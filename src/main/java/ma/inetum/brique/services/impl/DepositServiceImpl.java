package ma.inetum.brique.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ma.inetum.brique.bean.PieceComptableView;
import ma.inetum.brique.mapper.PieceComptableMapper;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.Deposit;
import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.ParamCompteFacturation;
import ma.inetum.brique.model.principale.ParamCompteVersement;
import ma.inetum.brique.model.principale.Parametrage;
import ma.inetum.brique.model.principale.ParametrageClient;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationDeposit;
import ma.inetum.brique.repository.metier.DepositDAO;
import ma.inetum.brique.repository.principale.ChargementRepository;
import ma.inetum.brique.repository.principale.DepositPRepository;
import ma.inetum.brique.repository.principale.ParametrageRepository;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.repository.principale.SimulationDepositRepository;
import ma.inetum.brique.services.ClientService;
import ma.inetum.brique.services.CompteFacturationService;
import ma.inetum.brique.services.CompteVersementService;
import ma.inetum.brique.services.DepositService;
import ma.inetum.brique.utilities.Constantes;
import ma.inetum.brique.utilities.NumeroPieceService;
import ma.inetum.brique.utilities.PieceComptableTool;

@Service
public class DepositServiceImpl implements DepositService {
	private final static Logger logger = LoggerFactory.getLogger(DepositServiceImpl.class);
	@Autowired
	private DepositPRepository depositRepository;

	@Autowired
	private ParametrageRepository parametrageRepository;
	@PersistenceContext(unitName = "Main")
	private EntityManager entityManager;
	@Autowired
	private PieceComptableRepository pieceComptableRepository;

	@Autowired
	private ChargementRepository chargementRepository;

	@Autowired
	private DepositDAO depositDAO;

	@Autowired
	private SimulationDepositRepository simulationRepository;

	@Autowired
	private NumeroPieceService numeroPieceService; 
	@Autowired
	private CompteFacturationService compteFacturationService; 
	@Autowired
	private CompteVersementService compteVersementService;
	@Autowired
	private ClientService clientService;

	private PieceComptableMapper mapper = PieceComptableMapper.getInstance();
	

	@Override
	public <T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c) {
		String tableName = c.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s v ", String.join(", ", header), tableName);
		// query += " where v.DATE_CHARGEMENT <= '" + date + "' and (FLAG_ENVOI = 0 or
		// FLAG_ENVOI is null)";
		String format = "yyyy-MM-dd";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		query += " where v.CREDATE <= to_date('" + dtf.format(date) + "', '"+ format +"') and (FLAG_ENVOI = 0 or FLAG_ENVOI is null)";
		Query statement = entityManager.createNativeQuery(query);
		List<Object[]> results = statement.getResultList();
		List<List<String>> details = new ArrayList<>();
		for (Object[] result : results) {
			List<String> list = new LinkedList<>();
			for (Object value : result) {
				if (value != null) {
					list.add(value.toString());
				} else {
					list.add("");
				}
			}
			details.add(list);
		}
		return details;
	}
	@Override
	public <T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c) {
		String tableName = c.getAnnotation(Table.class).name();
		String tableSimulation = SimulationDeposit.class.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s v, %s r ", String.join(", ", header), tableName, tableSimulation);
		query += " where r.VEHICULE_ID = v.ID and r.SIMULATION_ID = " + simulationId ;
		Query statement = entityManager.createNativeQuery(query);
		List<Object[]> results = statement.getResultList();
		List<List<String>> details = new ArrayList<>();
		for (Object[] result : results) {
			List<String> list = new LinkedList<>();
			for (Object value : result) {
				if (value != null) {
					list.add(value.toString());
				} else {
					list.add("");
				}
			}
			details.add(list);
		}
		return details;
	}
	@Override
	public <T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c) {
		String tableName = c.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s v ", String.join(", ", header), tableName);
		query += " where v.magic between " + first + " and " + (last != null ? last : 0l);
		Query statement = entityManager.createNativeQuery(query);
		logger.info("Logistic operation Query : " + query);
		List<Object[]> results = statement.getResultList();
		List<List<String>> details = new ArrayList<>();
		for (Object[] result : results) {
			List<String> list = new LinkedList<>();
			for (Object value : result) {
				if(value != null ) {
                    list.add(value.toString());
                } else {
                    list.add("");
                }
			}
			details.add(list);
		}
		return details;
	}

	public List<Deposit> findAllByDate(LocalDate date) {
		List<Deposit> deposits = new ArrayList<Deposit>();

		return deposits;
	}

	private List<Erreur> valider1(List<Deposit> deposits, Map<String, Map<String, String>> parametrages) {
		List<Erreur> errors = new ArrayList<>();
		Integer i = 0;
		Map<Map<String, Boolean>, ParamCompteFacturation> comptesFacturation = compteFacturationService.getAllToMap();
		Map<Map<String, String>, ParamCompteVersement> comptesVersement= compteVersementService.getAllToMap();
		Map<Map<String, String>, ParametrageClient> clients = clientService.getAllToMap();
		if (deposits.isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "La liste générer est vide "));
		}
		if(parametrages.get("marque") == null || parametrages.get("marque").isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "Aucune marque n'est paramétré "));
		}
		if(parametrages.get("site") == null || parametrages.get("site").isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "Aucune site n'est paramétré "));
		}
		if(comptesFacturation == null || comptesFacturation.isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "Aucune compte de facturation n'est paramétré "));
		}
		if(comptesVersement == null || comptesVersement.isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "Aucun compte de versement n'est paramétré "));
		}
		if(clients == null || clients.isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "Aucun client de facturation n'est paramétré "));
		}
		if(!errors.isEmpty()) {
			return errors;
		}
		for (Deposit deposit : deposits) {
			String site = deposit.getLocation() != null ? deposit.getLocation().trim() : null;
			String client = deposit.getAccount() != null ? deposit.getAccount().trim() : null;
			String marque = deposit.getFran() != null ? deposit.getFran().trim() : null;
			String modePaiement = deposit.getPaymcode() != null ? deposit.getPaymcode().trim() : null;
			if (site == null ) {
				errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i), "Le site n'est pas renseigné"));
				continue;
			} else if (parametrages.get("site").get(site) == null) {
				errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i),
						"Le site (" + site + ") n'est pas paramétré "));
				continue;
			}
			Map<String, String> clientKey = Map.of(site, client);
			Map<String, String> clientKeyD = Map.of(Constantes.DEFAULT_SITE, client);
			Map<String, Boolean> compteFacturationKey = Map.of(site, Boolean.FALSE);
			Map<String, String> compteVersementKey = Map.of(site, modePaiement);
			if (marque == null || marque.isBlank()) {
				//errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i), "La marque n'est pas renseigné"));
			} else if (parametrages.get("marque").get(marque) == null) {
				errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i),
						"La marque (" + marque + ") n'est pas paramétré "));
			}
			if (deposit.getCreationDate() == null) {
				errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i), "La date est vide"));
			}
			if(client == null ) {
				errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i), "Le client n'est pas renseigné"));
			}
			if(modePaiement == null ) {
				errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i), "Le mode de paiement n'est pas renseigné"));
			}
			
			if(!clients.containsKey(clientKey) && !clients.containsKey(clientKeyD)) {
				ParametrageClient defaultCustomer  = clientService.getDefaultCustomer();
				if (defaultCustomer == null) {
					errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i), "Le client (" + client + ") n'est pas paramétré pour le site (" + site + ")"));					
				}
			}
			if(!comptesFacturation.containsKey(compteFacturationKey)) {
				errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i), "Aucun compte TTC n'est pas paramétré pour le site (" + site + ")"));
			} else  {
				if(StringUtils.isEmpty(comptesFacturation.get(compteFacturationKey).getCompteClass4())) {
					errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i), "Le compte class 4 n'est pas renseigné pour le site (" + site + ")"));
				}
			}
			if(!comptesVersement.containsKey(compteVersementKey)) {
				errors.add(new Erreur(deposit.getMagic(), Long.valueOf(i), "Aucun compte n'est paramétré pour le mode de paiement (" + modePaiement + ") dans le site (" + site + ")"));
			}
		}
		return errors;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Map<Boolean, Object> simuler(Simulation simulation, LocalDate dateArret) throws CloneNotSupportedException {

		Map<Boolean, Object> resultat = new HashMap<>();

		try {

			List<Deposit> deposits = depositRepository.findAllBy(dateArret.plusDays(1l));
			Map<String, Map<String, String>> parametrages = new HashMap<>();
			List<Parametrage> marques = parametrageRepository.findByCategorie("marque");
			List<Parametrage> sites = parametrageRepository.findByCategorie("site");
			parametrages.put("marque",
					marques != null
							? marques.stream()
									.collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 ))
							: new HashMap<>());
			parametrages.put("site",
					sites != null
							? sites.stream()
									.collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 ))
							: new HashMap<>());
			List<Erreur> erreurs = valider1(deposits, parametrages);
			if (!erreurs.isEmpty()) {
				resultat.put(false, getErrorFormated(erreurs, simulation));
			} else {
				Map<Map<String, Boolean>, ParamCompteFacturation> comptesFacturation = compteFacturationService.getAllToMap();
				Map<Map<String, String>, ParamCompteVersement> comptesVersement= compteVersementService.getAllToMap();
				Map<Map<String, String>, ParametrageClient> clients = clientService.getAllToMap();
				List<PieceComptableView> pieces = new ArrayList<>();
				List<SimulationDeposit> depositList = new ArrayList<>();
				List<PieceComptable> listePieces = new ArrayList<>();
				Map<String, Long> mapNumeroPieces = new HashMap<>();
				for (Deposit deposit : deposits) {
					String site_ = deposit.getLocation().trim();
					String site = parametrages.get("site").get(site_).trim();
					String client_ = deposit.getAccount().trim();
					String marque_ = deposit.getFran()!= null ? deposit.getFran().trim() : null;
					String marque = (marque_!=null ?parametrages.get("marque").get(marque_).trim():null);
					marque = marque == null || marque.isBlank() ? Constantes.DEFAULT_MARQUE_CODE : marque;
					String modePaiement_ = deposit.getPaymcode().trim();
					ParametrageClient client = clients.get(Map.of(site_, client_));
					if(client == null) {
						client = clients.get(Map.of(Constantes.DEFAULT_SITE, client_));
						if (client == null) {
							client = clientService.getDefaultCustomer();
						}
					}
					ParamCompteFacturation compteFacturation = comptesFacturation.get(Map.of(site_, Boolean.FALSE));
					ParamCompteVersement compteVersement = comptesVersement.get(Map.of(site_, modePaiement_));
					depositList.add(new SimulationDeposit(deposit, simulation));
					PieceComptable piece = new PieceComptable();
					String codeJournale = Constantes.CODE_JRNL_DEPOSIT_VHL;
					if(mapNumeroPieces.get(codeJournale) == null ) {
						Long lastnumer = numeroPieceService.getNumeroPiece(codeJournale);
						mapNumeroPieces.put(codeJournale, lastnumer);
					} else {
						mapNumeroPieces.put(codeJournale, mapNumeroPieces.get(codeJournale) + 1l );
					}
					piece.setCodeJournale(codeJournale);
					piece.setDate(deposit.getDocdate());
					piece.setNumeroPiece(mapNumeroPieces.get(codeJournale));
					piece.setEnteteDoc(deposit.getCustname());
					piece.setFlux(simulation.getFlux());
					piece.setSimulation(simulation);
					/**
					 * TODO : Corriger la relation entre Piece comptable et VehicleOrder
					 */
					List<EcritureComptable> ecritures = new ArrayList<>();
					EcritureComptable ecriture = new EcritureComptable(deposit.getDocnum() + "",
							deposit.getOrdnum() + "", deposit.getChassis(), deposit.getFrandesc(),
							deposit.getVehicle() + "", deposit.getMagic()+"");
					ecriture.setPiece(piece);
					if (deposit.getDepvalue() == null || deposit.getDepvalue() == 0) {
						continue;
					}
					if (Constantes.MODES_PAIEMENT_DEPOSIT.contains(deposit.getPaymcode()) || Constantes.MODES_PAIEMENT_CHEQUE.contains(deposit.getPaymcode())
							|| Constantes.MODES_PAIEMENT_EFFET.contains(deposit.getPaymcode())
							|| Constantes.MODES_PAIEMENT_VIREMENT.contains(deposit.getPaymcode())
							|| Constantes.MODES_PAIEMENT_CARTE_N.contains(deposit.getPaymcode())
							|| Constantes.MODES_PAIEMENT_CARTE_E.contains(deposit.getPaymcode())) {
						// Ecriture comptable de Débit
						ecriture = (EcritureComptable) ecriture.clone();
						ecriture.setAccountDescription(compteVersement.getDescription());
						
						BigDecimal montantHT  = deposit.getDepvalue() != null ? BigDecimal.valueOf(deposit.getDepvalue()).abs() : BigDecimal.ZERO;
						BigDecimal montantTVA = deposit.getVatvalue() != null ? BigDecimal.valueOf(deposit.getVatvalue()).abs() : BigDecimal.ZERO;
						BigDecimal montantTTC = montantHT.add(montantTVA);
						BigDecimal prime = BigDecimal.ZERO;
						
						if (Constantes.MODES_PAIEMENT_CARTE_N.contains(deposit.getPaymcode())) {
							prime = montantTTC.multiply(BigDecimal.valueOf(Constantes.TAUX_CARTE_N)).setScale(2, RoundingMode.FLOOR);
						} else if(Constantes.MODES_PAIEMENT_CARTE_E.contains(deposit.getPaymcode())) {
							prime = montantTTC.multiply(BigDecimal.valueOf(Constantes.TAUX_CARTE_E)).setScale(2, RoundingMode.FLOOR);
						} 
						ecriture.setMontantMAD(montantTTC.add(prime).doubleValue());
						ecriture.setNcpt(compteVersement.getCompteGeneral());
						ecriture.setSens('D');
						ecritures.add(ecriture);
						
						// Ecriture comptable de Crédit
						ecriture = (EcritureComptable) ecriture.clone();
						ecriture.setAccountDescription(compteFacturation.getDesc3());
						ecriture.setMontantMAD(montantTTC.doubleValue());
						ecriture.setNcpt(
								String.join(".", compteFacturation.getCompteClass4(), client.getCodeFinance()));
						ecriture.setSens('C');
						ecritures.add(ecriture);
						if (Constantes.MODES_PAIEMENT_CARTE_N.contains(deposit.getPaymcode())) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setCostCenter(site + ".AVN." + marque);
							ecriture.setAccountDescription("Commission CMI");
							ecriture.setMontantMAD(prime.doubleValue());
							ecriture.setNcpt("614730");
							ecriture.setSens('C');
							ecritures.add(ecriture);
						} else if (Constantes.MODES_PAIEMENT_CARTE_E.contains(deposit.getPaymcode())) {
							ecriture = (EcritureComptable) ecriture.clone();
							ecriture.setCostCenter(site + ".AVN." + marque);
							ecriture.setAccountDescription("Commission CMI");
							ecriture.setMontantMAD(prime.doubleValue());
							ecriture.setNcpt("614730");
							ecriture.setSens('C');
							ecritures.add(ecriture);
						} 
					} 
					
					if(deposit.getSequence() != null && deposit.getSequence() == Constantes.DEPOSIT_SEQUENCE_2 && !ecritures.isEmpty()) {
			            for(EcritureComptable ecr : ecritures) {
			                if(ecr.getSens() == Constantes.SENS_CREDIT) {
			                	ecr.setSens(Constantes.SENS_DEBIT);
			                } else {
			                	ecr.setSens(Constantes.SENS_CREDIT);
			                }
			            }
			        }
					
					piece.setEcritures(ecritures);
					PieceComptableTool pcTool = new PieceComptableTool(piece, deposit.getMagic());
					if (!pcTool.isPieceValide()) {
						erreurs.addAll(pcTool.getErreurs());
						continue;
					}
					
					listePieces.add(piece);
					
					/*if(ecritures.size() == 0) {
						erreurs.add(new Erreur(deposit.getMagic(), 0l, "Cette pièce comptable ne posséde aucune écriture"));
					} else {
							
						double totalCredit = ecritures.stream()
								.filter(e -> e.getSens() == 'C')
								.mapToDouble(e -> e.getMontantMAD())
								.sum();
						double totalDebit= ecritures.stream()
								.filter(e -> e.getSens() == 'D')
								.mapToDouble(e -> e.getMontantMAD())
								.sum();
						if(totalCredit != totalDebit) {
							erreurs.add(new Erreur(deposit.getMagic(), 0l, "Total des montants en crédit ("+totalCredit+") différent du total des montants en débit ("+totalDebit+")"));
						} else {
							piece.setEcritures(ecritures);
							listePieces.add(piece);
						}
					}*/
				}
				if (!erreurs.isEmpty()) {
					resultat.put(false, getErrorFormated(erreurs, simulation));
				} else {
					for(var p : listePieces) {
						pieceComptableRepository.save(p);
						pieces.add(mapper.entityTobean(p));
						
					}
					simulationRepository.saveAll(depositList);
					resultat.put(true, pieces);
				}
			}
		} catch (Exception ex) {
			logger.error("L'erreur suivante s'est produite lors de la simulation", ex);
			String errDescr = "L'erreur suivante s'est produite lors de la simulation : " + ex.getMessage();
			List<Erreur> erreurs = new ArrayList<>();
			Erreur erreur = new Erreur(null, 0l, errDescr);
			erreur.setSimulation(simulation);
			erreurs.add(erreur);
			ex.printStackTrace();
			Map<String, List<String>> map = new HashMap<>();
			List<String> err = new ArrayList<>();
			err.add(errDescr);
			map.put("0", err);
			resultat.put(false, map);
		}
		return resultat;
	}

	public List<ma.inetum.brique.model.metier.Deposit> findAllForBatch(Long firstElementToLoad) {
		List<ma.inetum.brique.model.metier.Deposit> dpList = depositDAO.findAllForBatch();
		return dpList;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveAll(List<Deposit> list, Chargement newChargement) {
		Chargement chg = chargementRepository.getOne(newChargement.getId());
		for (Deposit Deposit : list) {
			Deposit.setChargement(chg);
		}
		depositRepository.saveAll(list);
	}

	@Transactional(value = "chainedTransactionManager")
	@Override
	public Boolean generer(Simulation simulation) {
		LocalDateTime now = LocalDateTime.now();
		List<Deposit> deposits = simulationRepository.findBySimulation(simulation);
		for (var deposit : deposits) {
			deposit.setDateEnvoi(now);
			deposit.setSent(true);
		}
		depositRepository.saveAll(deposits);
		return true;
	}

	@Override
	public void saveAll(List<Deposit> list) {
		// TODO Auto-generated method stub

	}

	private Map<String, List<String>> getErrorFormated(List<Erreur> erreurs, Simulation simulation) {
		Map<String, List<String>> map = new HashMap<>();
		if (!erreurs.isEmpty()) {
			simulation.setErreurs(erreurs);
			for (Erreur e : erreurs) {
				String key = e.getMagic()!= null?e.getMagic().toString():"";
				if (map.get(key) == null) {
					map.put(key, new ArrayList<>());
				}
				map.get(key).add(e.getDescription());
				e.setSimulation(simulation);
			}
//			resultat.put(false, map);
		}
		return map;
	}
}
