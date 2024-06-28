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

import ma.inetum.brique.bean.PieceComptableView;
import ma.inetum.brique.mapper.PieceComptableMapper;
import ma.inetum.brique.model.metier.VehicleFinalisationHeader;
import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.Parametrage;
import ma.inetum.brique.model.principale.ParametrageSupplier;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationVehicleFinalisation;
import ma.inetum.brique.model.principale.VehicleFinalisationDetailsP;
import ma.inetum.brique.model.principale.VehicleFinalisationHeaderP;
import ma.inetum.brique.repository.metier.VehicleFinalisationHeaderRepository;
import ma.inetum.brique.repository.principale.ParametrageRepository;
import ma.inetum.brique.repository.principale.ParametrageSupplierRepository;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.repository.principale.SimulationVehicleFinalisationRepository;
import ma.inetum.brique.repository.principale.VehicleFinalisationHeaderPRepository;
import ma.inetum.brique.services.VehicleFinalisationService;
import ma.inetum.brique.utilities.Constantes;
import ma.inetum.brique.utilities.NumeroPieceService;
import ma.inetum.brique.utilities.PieceComptableTool;

@Service
public class VehicleFinalisationServiceImpl implements VehicleFinalisationService {
	private final static Logger logger = LoggerFactory.getLogger(VehicleFinalisationServiceImpl.class);
	@Autowired
	private VehicleFinalisationHeaderRepository vehicleFinalisationHeaderMetierRepository;
	@Autowired
	private VehicleFinalisationHeaderPRepository vehicleFinalisationHeaderPRepository;
	@Autowired
	NumeroPieceService numeroPieceService;

	@Autowired
	PieceComptableRepository pieceComptableRepository;
	@Autowired
	private ParametrageRepository parametrageRepository;

	@Autowired
	private ParametrageSupplierRepository parametrageSupplierRepository;
	@Autowired
	SimulationVehicleFinalisationRepository simulationRepository;
	@Autowired
	NumeroPieceService numeroPiece;

	@PersistenceContext(unitName = "Main")
	EntityManager entityManager;
	private PieceComptableMapper mapper = PieceComptableMapper.getInstance();

	@Override
	public <T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c1, Class<?> c2) {
		date = date.plusDays(1);
		String tableName = c1.getAnnotation(Table.class).name();
		String tableNameDetails = c2.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s h, %s d ", String.join(", ", header), tableName,
				tableNameDetails);
		String format = "yyyy/MM/dd";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		query += " where h.magic = d.magic and h.DATE_CHARGEMENT <= to_date('" + dtf.format(date) + "', '" + format
				+ "') and (sent = 0 or sent is null)";
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
	public <T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c1, Class<?> c2) {
		String tableName = c1.getAnnotation(Table.class).name();
		String tableNameDetails = c2.getAnnotation(Table.class).name();
		String tableSimulation = SimulationVehicleFinalisation.class.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s h, %s d, %s r ", String.join(", ", header), tableName,
				tableNameDetails, tableSimulation);
		query += " where h.magic = d.magic and r.HEADER_ID = h.ID and r.SIMULATION_ID = " + simulationId;
		logger.info("Vehicule finalisation Query : " + query);
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

	private List<Erreur> valider(List<VehicleFinalisationHeaderP> headers,
			Map<String, Map<String, String>> parametrages, Map<Map<String, String>, String> parametragesSupplier) {
		List<Erreur> errors = new ArrayList<>();
		Integer numeroLigne = 0;
		if (headers.isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "La liste générer est vide "));
		}
		for (VehicleFinalisationHeaderP header : headers) {

			if (header.getFran() == null) {
				errors.add(new Erreur(header.getMagic(), Long.valueOf(numeroLigne), "Marque n'est pas renseigné"));
			} else if (parametrages.get("marque") == null || parametrages.get("marque").get(header.getFran()) == null) {
				errors.add(new Erreur(header.getMagic(), Long.valueOf(numeroLigne),
						"Marque (" + header.getFran() + ") n'existe pas "));
			}

			if (header.getVehicleFinalisationDetailsP() == null
					|| header.getVehicleFinalisationDetailsP().size() == 0) {
				errors.add(new Erreur(header.getMagic(), Long.valueOf(numeroLigne), "La liste des détails est vide"));
			} else {
				int j = 0;
				for (VehicleFinalisationDetailsP detail : header.getVehicleFinalisationDetailsP()) {
					j++;
					if ((detail.getBuycurr() == null) || (detail.getBuycurr().trim().length()==0)) {
						errors.add(new Erreur(detail.getMagic(), Long.valueOf(j),
								"Devise non renseignée pour la ligne du détail numéro " + j));
					} else if (parametrages.get("currency") == null
							|| parametrages.get("currency").get(detail.getBuycurr().trim()) == null) {
						errors.add(new Erreur(detail.getMagic(), Long.valueOf(j),
								"Devise (" + detail.getBuycurr() + ") non paramétrée "));
					}
					if ((header.getSupacc() == null) || (header.getSupacc().trim().length()==0)) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j), "supplier n'est pas renseigné"));
					} else {
						Map<String, String> cle = new HashMap<String, String>();
						cle.put(header.getSupacc().trim(), detail.getBuycurr().trim());
						if (parametragesSupplier.get(cle) == null) {
							errors.add(
									new Erreur(detail.getMagic(), Long.valueOf(j), "La devise (" + detail.getBuycurr()
											+ ") n'est paramétrée pour le fournisseur (" + header.getSupacc() + ") "));
						}
					}
//                    if(parametragesSupplier.get(header.getSupacc()) == null){
//                        errors.add(new Erreur(detail.getMagic(), Long.valueOf(j), "Code fournisseur (" + header.getSupacc() + ") non paramétré "));
//                    } else if (parametragesSupplier.get(header.getSupacc()).get(detail.getBuycurr()) == null) {
//                    	errors.add(new Erreur(detail.getMagic(), Long.valueOf(j), "La devise ("+ detail.getBuycurr() + ") n'est paramétrée pour le fournisseur (" + header.getSupacc() + ") "));
//                    }
				}
			}
		}
		return errors;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<Boolean, Object> simuler(Simulation simulation, LocalDate dateArret) throws CloneNotSupportedException {
		Map<Boolean, Object> resultat = new HashMap<>();
		List<VehicleFinalisationHeaderP> headersP = vehicleFinalisationHeaderPRepository
				.findAllByDate(dateArret.atStartOfDay().plusDays(1l).toLocalDate());
		Map<String, Map<String, String>> parametrages = new HashMap<>();
		// Map<String, Map<String, String>> parametragesSupplier = new HashMap<>();
		Map<Map<String, String>, String> parametragesSupplier = new HashMap<>();
		parametrageSupplierRepository.getAll().forEach(supplier -> {
			Map<String, String> cle = new HashMap<>();
			if ((supplier.getCodeMedtier() != null) && supplier.getCurrency() != null) {
				cle.put(supplier.getCodeMedtier(), supplier.getCurrency());
				parametragesSupplier.put(cle, supplier.getCodeFinance());
			} else {
				logger.warn(
						"Code fournisseur '" + supplier.getCodeMedtier() + "' ou sa devise '" + supplier.getCurrency()
								+ "' non renseigné au niveau du paramétrage. Ce fournisseur est ignoré");
			}
		});
		// parametrages.put("supplier", parametrageSupplierRepository.getAll().stream()
		// .collect(Collectors.toMap(ParametrageSupplier::getCodeMedtier,
		// ParametrageSupplier::getCodeFinance)));
		parametrages.put("marque", parametrageRepository.findByCategorie("marque").stream().collect(
				Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2)));
		parametrages.put("currency", parametrageRepository.findByCategorie("currency").stream().collect(
				Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2)));

		List<Erreur> erreurs = valider(headersP, parametrages, parametragesSupplier);
		if (!erreurs.isEmpty()) {
			simulation.setErreurs(erreurs);
			Map<String, List<String>> map = new HashMap<>();
			for (Erreur e : erreurs) {
				if (map.get(e.getMagic().toString()) == null) {
					map.put(e.getMagic().toString(), new ArrayList<>());
				}
				map.get(e.getMagic().toString()).add(e.getDescription());
				e.setSimulation(simulation);
			}
			resultat.put(false, map);
		} else {
			List<PieceComptableView> pieces = new ArrayList<>();
			List<SimulationVehicleFinalisation> vehicleFinalisationList = new ArrayList<>();
			Long lastnumerIMP = numeroPieceService.getNumeroPiece(Constantes.IMP_VHL);
			for (VehicleFinalisationHeaderP headerP : headersP) {
				PieceComptable piece = new PieceComptable();
				vehicleFinalisationList.add(new SimulationVehicleFinalisation(headerP, simulation));
				piece.setCodeJournale(Constantes.IMP_VHL);
				piece.setNumeroPiece(lastnumerIMP++);
				if (headerP.getVehicleFinalisationDetailsP() != null && 
						headerP.getVehicleFinalisationDetailsP().size() > 0 &&
						headerP.getVehicleFinalisationDetailsP().get(0).getFindate() != null) {
					LocalDate datePiece = headerP.getVehicleFinalisationDetailsP().get(0).getFindate();
					piece.setDate(datePiece);
				}
				if (headerP.getShipno() != null) {
					piece.setEnteteDoc(headerP.getShipno().toString());
				}
				piece.setFlux(simulation.getFlux());
				piece.setSimulation(simulation);

				List<EcritureComptable> ecritureComptableList = new ArrayList<>();
				for (VehicleFinalisationDetailsP vehicle : headerP.getVehicleFinalisationDetailsP()) {
					ParametrageSupplier parametrageSupplier = parametrageSupplierRepository
							.findByCodeMetierAndCurrency(headerP.getSupacc().trim(), vehicle.getBuycurr().trim());

					EcritureComptable ecritureD = new EcritureComptable();
					ecritureD.setSens('D');
					if (Constantes.VEHICULE.equals(parametrageSupplier.getType())) {
						ecritureD.setAccountDescription("Achats Véhicules Neufs Import");
						ecritureD.setNcpt("611100");
					} else if (Constantes.EQUIPEMENT.equals(parametrageSupplier.getType())) {
						ecritureD.setAccountDescription("Achat équipement MB VN");
						ecritureD.setNcpt("611170");
					}
					BigDecimal freight = BigDecimal.valueOf(vehicle.getFreight());
					//double freight = ((int) (vehicle.getFreight() * 100)) / 100.0;
					// freight.setScale(2);
					BigDecimal cons = BigDecimal.valueOf(vehicle.getCons());
					//double cons = ((int) (vehicle.getCons() * 100)) / 100.0;
					// cons.setScale(2);
					BigDecimal montantMAD = freight.add(cons);
					BigDecimal devise = BigDecimal.valueOf(vehicle.getBuyexch());
					BigDecimal montantCurr = montantMAD.multiply(devise);
					if (montantMAD.compareTo(BigDecimal.ZERO) == 0) {
						continue;
					}
					ecritureD.setMontantMAD(montantMAD.doubleValue());
					ecritureD.setMontant(montantCurr.setScale(2, RoundingMode.HALF_UP).doubleValue());
					ecritureD.setCurrencyCode(parametrages.get("currency").get(vehicle.getBuycurr().trim()));
					ecritureD.setPiece(piece);
					ecritureD.setRef1(headerP.getPinvno());
					if (headerP.getImcommno() != null) {
						ecritureD.setRef2(headerP.getImcommno().toString());
					}
					ecritureD.setRef3(vehicle.getChassis());
					ecritureD.setRef4(headerP.getShipno());
					if (vehicle.getVehicle() != null) {
						ecritureD.setRef5(vehicle.getVehicle().toString());
					}
					ecritureD.setRef6(headerP.getSupplier());
					ecritureD.setCostCenter(
							String.join(".", "SSIEGE", "AVN", parametrages.get("marque").get(headerP.getFran())));
					EcritureComptable ecritureC = (EcritureComptable) ecritureD.clone();
					ecritureC.setSens('C');
					ecritureC.setAccountDescription(headerP.getSupplier());
					ecritureC.setCostCenter("");
					ecritureC.setMontant(montantCurr.setScale(2, RoundingMode.HALF_UP).doubleValue());
					ecritureC.setMontantMAD(montantMAD.doubleValue());
					ecritureC.setCurrencyCode(parametrages.get("currency").get(vehicle.getBuycurr().trim()));
					Map<String, String> cle = new HashMap<String, String>();
					cle.put(headerP.getSupacc().trim(), vehicle.getBuycurr().trim());
					if (Constantes.VEHICULE.equals(parametrageSupplier.getType())) {
						String firstElementAccountNumber = parametrageRepository.findByCode(headerP.getFran(), "marque")
								.getAddtionalField();
						/*
						 * M5(Mercedes_VP) : firstElementAccountNumber = 441120 V(Mercedes_VUL) :
						 * firstElementAccountNumber = 441121 T(Truck) : firstElementAccountNumber =
						 * 441123 B(Bus) : firstElementAccountNumber = 441124
						 */
						ecritureC.setNcpt(String.join(".", firstElementAccountNumber, parametragesSupplier.get(cle)));
					} else if (Constantes.EQUIPEMENT.equals(parametrageSupplier.getType())) {
						ecritureC.setNcpt(String.join(".", "441810", parametragesSupplier.get(cle)));
					}
					ecritureComptableList.add(ecritureC);
					ecritureComptableList.add(ecritureD);
				}

				piece.setEcritures(ecritureComptableList);
				PieceComptableTool pcTool = new PieceComptableTool(piece);
				if (!pcTool.isPieceValide()) {
					erreurs.addAll(pcTool.getErreurs());
					continue;
				}
				if (!erreurs.isEmpty()) {
					resultat.put(false, getErrorFormated(erreurs, simulation));
				} else {
					pieceComptableRepository.save(piece);
					pieces.add(mapper.entityTobean(piece));
					resultat.put(true, pieces);
				}				
			}
			simulationRepository.saveAll(vehicleFinalisationList);
			resultat.put(true, pieces);
		}
		return resultat;
	}

	public List<VehicleFinalisationHeader> findAllForBatch(Long firstElementToLoad) {
		return vehicleFinalisationHeaderMetierRepository.findAllForBatch(firstElementToLoad);
	}

	public void saveAll(List<VehicleFinalisationHeaderP> listHeader) {
		vehicleFinalisationHeaderPRepository.saveAll(listHeader);
	}

	@Transactional//(value = "chainedTransactionManager")
	@Override
	public Boolean generer(Simulation simulation) {
		LocalDateTime now = LocalDateTime.now();
		List<VehicleFinalisationHeaderP> headers = simulationRepository.findBySimulation(simulation);
		for (var header : headers) {
			header.setDateEnvoi(now);
			header.setSent(true);
		}
		vehicleFinalisationHeaderPRepository.saveAll(headers);
		return true;
	}

	/**
	 *
	 * @param header
	 * @param first
	 * @param last
	 * @param c1
	 * @param c2
	 * @return
	 * @param <T>
	 */
	@Override
	public <T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c1, Class<?> c2) {
		String tableName = c1.getAnnotation(Table.class).name();
		String tableNameDetails = c2.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s h, %s d ", String.join(", ", header), tableName,
				tableNameDetails);
		query += " where h.magic = d.magic and h.magic between " + first + " and " + last;
		logger.info("Vehicle Finalisation Query : " + query);
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
