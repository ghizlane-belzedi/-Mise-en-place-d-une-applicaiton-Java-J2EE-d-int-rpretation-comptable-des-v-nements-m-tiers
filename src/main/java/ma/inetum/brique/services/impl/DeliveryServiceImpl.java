package ma.inetum.brique.services.impl;

import static ma.inetum.brique.utilities.Constantes.FVN_EXO_LIV;
import static ma.inetum.brique.utilities.Constantes.FVN_TTC_LIV;

import java.math.BigDecimal;
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
import ma.inetum.brique.model.metier.Delivery;
import ma.inetum.brique.model.principale.DeliveryP;
import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.Parametrage;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationDelivery;
import ma.inetum.brique.repository.metier.DeliveryRepository;
import ma.inetum.brique.repository.principale.DeliveryPRepository;
import ma.inetum.brique.repository.principale.ParametrageRepository;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.repository.principale.SimulationDeliveryRepository;
import ma.inetum.brique.services.DeliveryService;
import ma.inetum.brique.utilities.Constantes;
import ma.inetum.brique.utilities.NumeroPieceService;
import ma.inetum.brique.utilities.PieceComptableTool;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	private final static Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);
	@Autowired
	DeliveryPRepository deliveryPRepository;
	@Autowired
	DeliveryRepository deliveryRepository;

	@Autowired
	private ParametrageRepository parametrageRepository;

	@Autowired
	SimulationDeliveryRepository simulationRepository;
	@Autowired
	PieceComptableRepository pieceComptableRepository;
	@Autowired
	NumeroPieceService numeroPiece;
	@PersistenceContext(unitName = "Main")
	EntityManager entityManager;
	@Autowired
	private NumeroPieceService numeroPieceService;
	private PieceComptableMapper mapper = PieceComptableMapper.getInstance();

	@Override
	public List<Delivery> findAllForBatch(Long firstElementToLoad) {
		return deliveryRepository.findAllForBatch(firstElementToLoad);
	}

	@Override
	public void saveAll(List<DeliveryP> deliveryPList) {
		deliveryPRepository.saveAll(deliveryPList);
	}

	private List<Erreur> valider(List<DeliveryP> deliveryPList, Map<String, Map<String, String>> parametrages) {
		List<Erreur> errors = new ArrayList<>();
		Integer numeroLigne = 0;
		if (deliveryPList.isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "La liste générer est vide "));
		}
		for (DeliveryP deliveryP : deliveryPList) {
			numeroLigne = numeroLigne + 1;
			if (deliveryP.getSellLocn() == null) {
				errors.add(new Erreur(deliveryP.getMagic(), Long.valueOf(0), "Site n'est pas renseigné"));
			} else if (parametrages.get("site") == null
					|| parametrages.get("site").get(deliveryP.getSellLocn().trim()) == null) {
				errors.add(new Erreur(deliveryP.getMagic(), Long.valueOf(0),
						"Le Site (" + deliveryP.getSellLocn() + ") n'est pas paramétré"));
			}

			if (deliveryP.getFran() == null) {
				errors.add(new Erreur(deliveryP.getMagic(), Long.valueOf(0), "Marque n'est pas renseigné"));
			} else if (parametrages.get("marque") == null
					|| parametrages.get("marque").get(deliveryP.getFran().trim()) == null) {
				errors.add(new Erreur(deliveryP.getMagic(), Long.valueOf(0),
						"La Marque (" + deliveryP.getFran() + ") n'est pas paramétré "));
			}

		}
		return errors;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<Boolean, Object> simuler(Simulation simulation, LocalDate dateArret) throws CloneNotSupportedException {
		Map<Boolean, Object> resultat = new HashMap<>();
		List<DeliveryP> list = deliveryPRepository.findAllByDate(dateArret.atStartOfDay().plusDays(1l).toLocalDate());
		Map<String, Map<String, String>> parametrages = new HashMap<>();
		parametrages.put("marque", parametrageRepository.findByCategorie("marque").stream().collect(
				Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2)));
		parametrages.put("site", parametrageRepository.findByCategorie("site").stream().collect(
				Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2)));
		List<PieceComptable> piecesDelivery = new ArrayList<>();
		List<Erreur> erreurs = valider(list, parametrages);
		if (!erreurs.isEmpty()) {
			resultat.put(false, getErrorFormated(erreurs, simulation));
		} else {
			List<PieceComptableView> pieces = new ArrayList<>();
			List<SimulationDelivery> simulationDeliveryList = new ArrayList<>();

			for (DeliveryP element : list) {
				PieceComptable piece = new PieceComptable();
				simulationDeliveryList.add(new SimulationDelivery(element, simulation));
				if (element.getSalenet() == null || element.getSalenet() == 0) {
					erreurs.add(new Erreur(element.getMagic(), 0l, "Le montant HT de la pièce est vide"));
					continue;
				}
				piece.setDate(element.getDelDate());
				Long lastnumerTTC = numeroPieceService.getNumeroPiece(FVN_TTC_LIV);
				Long lastnumerEXO = numeroPieceService.getNumeroPiece(FVN_EXO_LIV);
				// client exonéré
				if (element.getSalevat() == null || element.getSalevat() == 0) {
					piece.setCodeJournale(FVN_EXO_LIV);
					piece.setNumeroPiece(lastnumerEXO++);
				} else if (element.getSalevat() != null && element.getSalevat() != 0) {
					piece.setCodeJournale(FVN_TTC_LIV);
					piece.setNumeroPiece(lastnumerTTC++);
				}
				if(element.getInvAcc().equalsIgnoreCase(Constantes.DEFAULT_CUSTOMER_CODE) || element.getInvAcc().startsWith("V")) {
					piece.setEnteteDoc(element.getCustName() );
				} else  {
					piece.setEnteteDoc(element.getInvName() + "/" + element.getCustName() );
				}
//				piece.setEnteteDoc(element.getInvName() + " " + element.getCustName() );
				piece.setFlux(simulation.getFlux());
				piece.setSimulation(simulation);

				EcritureComptable ecritureC = new EcritureComptable();
				ecritureC.setSens('C');
				ecritureC.setAccountDescription("Vente VN (Annulation FNL)");
				ecritureC.setCostCenter(String.join(".", parametrages.get("site").get(element.getSellLocn().trim()),
						"AVN", parametrages.get("marque").get(element.getFran().trim())));
				BigDecimal saleNet = BigDecimal.valueOf(element.getSalenet());
				saleNet = saleNet
						.subtract(element.getRecpri1() !=null?BigDecimal.valueOf(element.getRecpri1()) :BigDecimal.ZERO)
						.subtract(element.getRecpri2() !=null?BigDecimal.valueOf(element.getRecpri2()) :BigDecimal.ZERO)
						.subtract(element.getRecpri3() !=null?BigDecimal.valueOf(element.getRecpri3()) :BigDecimal.ZERO)
						.subtract(element.getRecpri5() !=null?BigDecimal.valueOf(element.getRecpri5()) :BigDecimal.ZERO)
						.subtract(element.getRecpri6() !=null?BigDecimal.valueOf(element.getRecpri6()) :BigDecimal.ZERO)
						.subtract(element.getRecpri7() !=null?BigDecimal.valueOf(element.getRecpri7()) :BigDecimal.ZERO)
						.subtract(element.getRecpri8() !=null?BigDecimal.valueOf(element.getRecpri8()) :BigDecimal.ZERO)
						.subtract(element.getRecpri9() !=null?BigDecimal.valueOf(element.getRecpri9()) :BigDecimal.ZERO)
						.subtract(element.getRecpri10()!=null?BigDecimal.valueOf(element.getRecpri10()):BigDecimal.ZERO)
						.subtract(element.getRecpri11()!=null?BigDecimal.valueOf(element.getRecpri11()):BigDecimal.ZERO)
						.subtract(element.getRecpri12()!=null?BigDecimal.valueOf(element.getRecpri12()):BigDecimal.ZERO)
						.subtract(element.getRecpri13()!=null?BigDecimal.valueOf(element.getRecpri13()):BigDecimal.ZERO)
						.subtract(element.getRecpri14()!=null?BigDecimal.valueOf(element.getRecpri14()):BigDecimal.ZERO)
						.subtract(element.getRecpri15()!=null?BigDecimal.valueOf(element.getRecpri15()):BigDecimal.ZERO)
						;
				
				ecritureC.setMontantMAD(saleNet.doubleValue());
				// client exonéré
				if (element.getSalevat() == null || element.getSalevat() == 0) {
					ecritureC.setNcpt("711101");
				} else if (element.getSalevat() != null && element.getSalevat() != 0) {
					ecritureC.setNcpt("711100");
				}
				if (element.getInvNo() != null) {
					ecritureC.setRef1(element.getInvNo().toString());
				}
				if (element.getCustOrd() != null) {
					ecritureC.setRef2(element.getCustOrd().toString());
				}
				ecritureC.setRef3(element.getChassis());
				if (element.getVehicle() != null) {
					ecritureC.setRef4(element.getVehicle().toString());
				}
				if (element.getDelNote() != null) {
					ecritureC.setRef5(element.getDelNote().toString());
				}
				if (element.getDelDate() != null) {
					ecritureC.setRef6(element.getDelDate().toString());
				}
				ecritureC.setPiece(piece);

				EcritureComptable ecritureD = (EcritureComptable) ecritureC.clone();
				ecritureD.setSens('D');
				ecritureD.setAccountDescription("Ventes VN (Facturation livrée)");
				// client exonéré
				if (element.getSalevat() == null || element.getSalevat() == 0) {
					ecritureD.setNcpt("711121");
				} else if (element.getSalevat() != null && element.getSalevat() != 0) {
					ecritureD.setNcpt("711120");
				}
				ecritureD.setCostCenter(String.join(".", parametrages.get("site").get(element.getSellLocn().trim()),
						"AVN", parametrages.get("marque").get(element.getFran().trim())));
				ecritureD.setMontantMAD(saleNet.doubleValue());
				List<EcritureComptable> ecritureComptableList = new ArrayList<>();
				ecritureComptableList.add(ecritureC);
				ecritureComptableList.add(ecritureD);
				piece.setEcritures(ecritureComptableList);
				PieceComptableTool pcTool = new PieceComptableTool(piece);
				if (!pcTool.isPieceValide()) {
					erreurs.addAll(pcTool.getErreurs());
				} else {
					piecesDelivery.add(piece);						
				}

			}
			if (erreurs.size() == 0) {
				for (var p : piecesDelivery) {
					pieceComptableRepository.save(p);
					pieces.add(mapper.entityTobean(p));

				}
				resultat.put(true, pieces);
			} else {
				resultat.put(false, getErrorFormated(erreurs, simulation));
			}
			simulationRepository.saveAll(simulationDeliveryList);
		}
		return resultat;
	}

	@Transactional(value = "chainedTransactionManager")
	@Override
	public Boolean generer(Simulation simulation) {
		LocalDateTime now = LocalDateTime.now();
		List<DeliveryP> deliveryPList = simulationRepository.findBySimulation(simulation);
		for (var delivery : deliveryPList) {
			delivery.setDateEnvoi(now);
			delivery.setSent(true);
		}
		deliveryPRepository.saveAll(deliveryPList);
		return true;
	}

	@Override
	public <T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c) {
		String tableName = c.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s v ", String.join(", ", header), tableName);
		String format = "yyyy-MM-dd";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		query += " where v.CREDATE <= to_date('" + dtf.format(date) + "', '"+ format +"') and (sent = 0 or sent is null)";
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
		String tableSimulation = SimulationDelivery.class.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s v, %s r ", String.join(", ", header), tableName,
				tableSimulation);
		query += " where r.DELIVERY_ID = v.ID and r.SIMULATION_ID = " + simulationId;
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
				if (map.get(e.getMagic().toString()) == null) {
					map.put(e.getMagic().toString(), new ArrayList<>());
				}
				map.get(e.getMagic().toString()).add(e.getDescription());
				e.setSimulation(simulation);
			}
//			resultat.put(false, map);
		}
		return map;
	}

}
