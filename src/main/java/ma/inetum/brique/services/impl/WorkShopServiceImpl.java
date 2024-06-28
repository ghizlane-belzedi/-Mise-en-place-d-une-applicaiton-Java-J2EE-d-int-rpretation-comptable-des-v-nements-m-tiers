package ma.inetum.brique.services.impl;

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
import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.Parametrage;
import ma.inetum.brique.model.principale.ParamCompteCosting;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationWorkShop;
import ma.inetum.brique.model.principale.WorkShopDetails;
import ma.inetum.brique.model.principale.WorkShopHeader;
import ma.inetum.brique.repository.metier.WorkShopHeaderRepository;
import ma.inetum.brique.repository.principale.ErreurRepository;
import ma.inetum.brique.repository.principale.ParametrageRepository;
import ma.inetum.brique.repository.principale.ParamCompteCostingRepository;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.repository.principale.SimulationWorkShopRepository;
import ma.inetum.brique.repository.principale.WorkShopHeaderPRepository;
import ma.inetum.brique.services.WorkShopService;
import ma.inetum.brique.utilities.Constantes;
import ma.inetum.brique.utilities.NumeroPieceService;

@Service
public class WorkShopServiceImpl implements WorkShopService {

	private final static Logger logger = LoggerFactory.getLogger(WorkShopServiceImpl.class);
	
	@Autowired
	private WorkShopHeaderPRepository workShopHeaderPRepository;
	
	@Autowired
	private ParametrageRepository parametrageRepository;
	@PersistenceContext(unitName = "Main")
	EntityManager entityManager;
	@Autowired
	PieceComptableRepository pieceComptableRepository;
	@Autowired
	ErreurRepository erreurRepository;
	@Autowired
	ParamCompteCostingRepository schemaComptableRepository;
	@Autowired
	private WorkShopHeaderRepository workShopHeaderMRepository;
	@Autowired
	SimulationWorkShopRepository simulationRepository;
	
	private PieceComptableMapper mapper = PieceComptableMapper.getInstance();
	@Autowired
	private NumeroPieceService numeroPieceService;
	@Override
	public <T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c1, Class<?> c2) {
		String tableName = c1.getAnnotation(Table.class).name();
		String tableNameDetails = c2.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s h, %s d ", String.join(", ", header), tableName, tableNameDetails);
		String format = "yyyy-MM-dd";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        query += " where h.magic = d.magic and h.DATE_CHARGEMENT  <= to_date('" + dtf.format(date) + "', '"+ format +"') and (FLAG_ENVOI = 0 or FLAG_ENVOI is null)";
		Query statement = entityManager.createNativeQuery(query);
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
	@Override
	public <T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c1, Class<?> c2) {
		String tableName = c1.getAnnotation(Table.class).name();
		String tableNameDetails = c2.getAnnotation(Table.class).name();
		String tableSimulation = SimulationWorkShop.class.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s h, %s d, %s r ", String.join(", ", header), tableName, tableNameDetails, tableSimulation);
		query += " where h.magic = d.magic and r.HEADER_ID = h.ID and r.SIMULATION_ID = " + simulationId ;
		logger.info("Workshop Query : " + query);
		Query statement = entityManager.createNativeQuery(query);
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
	@Override
	public <T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c1, Class<?> c2) {
		String tableName = c1.getAnnotation(Table.class).name();
		String tableNameDetails = c2.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s h, %s d ", String.join(", ", header), tableName, tableNameDetails);
		query += " where h.magic = d.magic and h.magic between " + first + " and " + last;
		logger.info("WorkShop Query : " + query);
		Query statement = entityManager.createNativeQuery(query);
		List<Object[]> results = statement.getResultList();
		List<List<String>> details = new ArrayList<>();
		for (Object[] result : results) {
			List<String> list = new LinkedList<>();
			for (Object value : result) {
				if(value != null) {
					list.add(value.toString());
				} else {
					list.add("");
				}
			}
			details.add(list);
		}
		return details;
	}

	public List<WorkShopHeader> findAllByDate(LocalDate date) {
		List<WorkShopHeader> headers = new ArrayList<WorkShopHeader>();

		return headers;
	}

	
	private List<Erreur> valider1(List<WorkShopHeader> headers, Map<String, Map<String, String>> parametrages) {
		List<Erreur> errors = new ArrayList<>();
		Integer i = 0;
		if (headers.isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "La liste générer est vide "));
		}
		for (WorkShopHeader header : headers) {
			if (header.getVatCode() == null) {
				errors.add(new Erreur(header.getMagic(), Long.valueOf(i), "Code TVA n'est pas renseigné"));
			} else if (parametrages.get("tva") == null
					|| parametrages.get("tva").get(header.getVatCode()) == null) {
				errors.add(new Erreur(header.getMagic(), Long.valueOf(i), "Code TVA (" + header.getVatCode() +") n'existe pas "));
			}
			if (header.getDetails() == null || header.getDetails().size() == 0) {
				errors.add(new Erreur(header.getMagic(), Long.valueOf(i), "La liste des détails est vide"));
			} else {
				int j = 0;
				for(WorkShopDetails detail : header.getDetails()) {
					j++;
					if (detail.getVatCode() == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j), "Code TVA n'est pas renseigné pour la ligne du détail numéro " + j));
					} else if (parametrages.get("tva") == null
							|| parametrages.get("tva").get(detail.getVatCode().trim()) == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j), "Code TVA (" + detail.getVatCode() +") n'existe pas "));
					}
					if (detail.getFran() == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j), "Marque n'est pas renseigné pour la ligne du détail numéro " + j));
					} else if (parametrages.get("marque") == null
							|| parametrages.get("marque").get(detail.getFran().trim()) == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j), "Marque (" + detail.getFran() +") n'existe pas "));
					}
					if (detail.getLocn() == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j), "Site n'est pas renseigné pour la ligne du détail numéro " + j));
					} else if (parametrages.get("site") == null
							|| parametrages.get("site").get(detail.getLocn().trim()) == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j), "Site (" + detail.getLocn() +") n'existe pas "));
					}
				}
//				if(totalTva.doubleValue() != header.getVatValue().doubleValue()) {
//					errors.add(new Erreur(header.getId(), Long.valueOf(i), "somme TVA par ligne n'est pas égale au Montant Total TVA de entête"));
//				}
//				if(totalIndividual.doubleValue() != header.getInValue().doubleValue() ) {
//					errors.add(new Erreur(header.getId(), Long.valueOf(i), "somme des montants individuels par ligne n'est pas égale au Montant Total de la facture"));
//				}
			}
		}
		return errors;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<Boolean, Object> simuler(Simulation simulation, LocalDate dateArret) throws CloneNotSupportedException {
		Map<Boolean, Object> resultat = new HashMap<>();
		List<WorkShopHeader> headers = workShopHeaderPRepository.findAllBy(dateArret.atStartOfDay().plusDays(1l).toLocalDate());
		Map<String, Map<String, String>> parametrages = new HashMap<>();
//		parametrages.put("supplier", parametrageRepository.findByCategorie("supplier").stream()
//				.collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 )));
		parametrages.put("marque", parametrageRepository.findByCategorie("marque").stream()
				.collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 )));
		parametrages.put("tva", parametrageRepository.findByCategorie("tva").stream()
				.collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 )));
		parametrages.put("site", parametrageRepository.findByCategorie("site").stream()
				.collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 )));
		List<Erreur> erreurs = valider1(headers, parametrages);
		List<PieceComptable> piecesWorkShop = new ArrayList<>();
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
			List<SimulationWorkShop> workshopList = new ArrayList<>();
			Long lastnumerS = numeroPieceService.getNumeroPiece(Constantes.CODE_JRNL_STOCK_VHL);
			
			for (WorkShopHeader header : headers) {
				PieceComptable piece = new PieceComptable();
				workshopList.add(new SimulationWorkShop(header, simulation));
				piece.setCodeJournale(Constantes.CODE_JRNL_STOCK_VHL);
				piece.setDate(header.getInvDate());
				piece.setNumeroPiece(lastnumerS++);
				piece.setEnteteDoc("Auto Nejma");
				piece.setFlux(simulation.getFlux());
				piece.setSimulation(simulation);
//				piece.setVehicule(vehicule);
				Map<String, Map<Long, List<WorkShopDetails>>> map = header.getDetails().stream()
						.collect(Collectors.groupingBy(e -> e.getLocn().trim(), 
								Collectors.groupingBy(e -> e.getVehicle())));
				for(var location : map.keySet()) {
					for(var vehicule : map.get(location).keySet()) {
						double totale = ((int)(( map.get(location).get(vehicule).stream().mapToDouble(e->e.getInValue()).sum() )* 100)) / 100.0 ;
						if(totale == 0) {
							continue;
						}
						EcritureComptable ecriture = new EcritureComptable();
						ecriture.setRef1(header.getInvNo() != null ? header.getInvNo().toString() : "");
						ecriture.setRef2(header.getWipNo() != null ? header.getWipNo().toString() : "");
						ecriture.setRef3(String.join(",", header.getDetails().stream().map(e -> e.getChassis().toString()).distinct().collect(Collectors.toList())));
						ecriture.setRef4(String.join(",", header.getDetails().stream().map(e -> e.getAnalDesc()).distinct().collect(Collectors.toList())));
						ecriture.setRef5(String.join(",", header.getDetails().stream().map(e -> e.getVehicle().toString()).distinct().collect(Collectors.toList())));
						ecriture.setRef6(String.join(",", header.getDetails().stream().map(e -> e.getFranDesc().toString()).distinct().collect(Collectors.toList())));
						ecriture.setPiece(piece);
						ecriture.setNcpt("311100");
						ecriture.setMontantMAD(totale);
						ecriture.setCostCenter( parametrages.get("site").get(location) + ".AVN." + parametrages.get("marque").get(map.get(location).get(vehicule).get(0).getFran().trim()));
						ecriture.setSens('D');
						ecriture.setAccountDescription("Coût interne additionnel sur stock véhicule (" + String.join(", ", map.get(location).get(vehicule).stream().map(e -> e.getAnalDesc()).collect(Collectors.toList()) ) + ")");
						piece.getEcritures().add(ecriture);
						ecriture = new EcritureComptable();
						ecriture.setRef1(header.getInvNo() != null ? header.getInvNo().toString() : "");
						ecriture.setRef2(header.getWipNo() != null ? header.getWipNo().toString() : "");
						ecriture.setRef3(String.join(",", header.getDetails().stream().map(e -> e.getChassis().toString()).distinct().collect(Collectors.toList())));
						ecriture.setRef4(String.join(",", header.getDetails().stream().map(e -> e.getAnalDesc()).distinct().collect(Collectors.toList())));
						ecriture.setRef5(String.join(",", header.getDetails().stream().map(e -> e.getVehicle().toString()).distinct().collect(Collectors.toList())));
						ecriture.setRef6(String.join(",", header.getDetails().stream().map(e -> e.getFranDesc().toString()).distinct().collect(Collectors.toList())));
						ecriture.setPiece(piece);
						ecriture.setSens('C');
						ecriture.setAccountDescription("Variation Stock Vles Neufs");
						ecriture.setNcpt("611400");
						ecriture.setMontantMAD(totale);
						ecriture.setCostCenter( parametrages.get("site").get(location) + ".AVN." + parametrages.get("marque").get(map.get(location).get(vehicule).get(0).getFran().trim()));
						piece.getEcritures().add(ecriture);
					}
				}
				if(piece.getEcritures().size() > 0) {
					pieceComptableRepository.save(piece);
					pieces.add(mapper.entityTobean(piece));
					piecesWorkShop.add(piece);
				}
			}
			simulationRepository.saveAll(workshopList);
//			pieceComptableRepository.saveAll(piecesWorkShop);
			resultat.put(true, pieces);
		}
		return resultat;
	}
	
	public  List<ma.inetum.brique.model.metier.WorkShopHeader> findAllForBatch(Long firstElementToLoad) {
		return workShopHeaderMRepository.findAllForBatch(firstElementToLoad);
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean saveAll(List<WorkShopHeader> list) {
		List<Erreur> erreurs = new ArrayList<>();
		for(var element : list) {
			if(element.getDetails() == null || element.getDetails().size() ==0) {
				Erreur erreur = new Erreur(element.getMagic(), null, "Element n'a pas de détails");
				erreur.setChargement(element.getChargement());
				erreurs.add(new Erreur(element.getMagic(), 0l, "Element n'a pas de détails"));
			}
		}
		if(erreurs.isEmpty()) {
			workShopHeaderPRepository.saveAll(list);
			return true;
		} else {
			erreurRepository.saveAll(erreurs);
			return false;
		}
	}
	@Transactional(value = "chainedTransactionManager")
	@Override
	public Boolean generer(Simulation simulation) {
		LocalDateTime now = LocalDateTime.now();
		List<WorkShopHeader> headers = simulationRepository.findBySimulation(simulation);
		for (var header : headers) {
			header.setDateEnvoi(now);
			header.setSent(true);
		}
		workShopHeaderPRepository.saveAll(headers);
		return true;
	}
}
