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
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.Parametrage;
import ma.inetum.brique.model.principale.ParametrageSupplier;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationVehiculeOrder;
import ma.inetum.brique.model.principale.VehiculeOrder;
import ma.inetum.brique.repository.metier.VehiculeOrderRepository;
import ma.inetum.brique.repository.principale.ChargementRepository;
import ma.inetum.brique.repository.principale.ErreurRepository;
import ma.inetum.brique.repository.principale.ParametrageRepository;
import ma.inetum.brique.repository.principale.ParametrageSupplierRepository;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.repository.principale.SimulationVehiculeOrdreRepository;
import ma.inetum.brique.repository.principale.VehiculeOrderPRepository;
import ma.inetum.brique.services.VehiculeOrderService;
import ma.inetum.brique.utilities.Constantes;

@Service
public class VehiculeOrderServiceImpl implements VehiculeOrderService {

	private final static Logger logger = LoggerFactory.getLogger(VehiculeOrderServiceImpl.class);
	@Autowired
	private VehiculeOrderPRepository vehiculeOrderRepository;

	@Autowired
	private ParametrageRepository parametrageRepository;
	@PersistenceContext(unitName = "Main")
	EntityManager entityManager;
	@Autowired
	PieceComptableRepository pieceComptableRepository;
	@Autowired
	ErreurRepository erreurRepository;

	@Autowired
	ChargementRepository chargementRepository;

	@Autowired
	private VehiculeOrderRepository vehiculeOrderMRepository;

	@Autowired
	private ParametrageSupplierRepository parametrageSupplierRepository;

	@Autowired
	SimulationVehiculeOrdreRepository simulationRepository;

	private PieceComptableMapper mapper = PieceComptableMapper.getInstance();


	@Override
	public <T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c) {
		String tableName = c.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s v ", String.join(",", header), tableName);
		//query += " where v.DATE_CHARGEMENT  <= '" + date + "' and (FLAG_ENVOI = 0 or FLAG_ENVOI is null)";
		String format = "yyyy-MM-dd";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		query += " where v.DATE_CHARGEMENT <= to_date('" + dtf.format(date) + "', '"+ format +"') and (FLAG_ENVOI = 0 or FLAG_ENVOI is null)";
		Query statement = entityManager.createNativeQuery(query);
		logger.info("Vehicle Order Query : " + query);
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
	public <T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c) {
		String tableName = c.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s v ", String.join(", ", header), tableName);
		query += " where v.magic between " + first + " and " + (last!=null?last:0l);
		Query statement = entityManager.createNativeQuery(query);
		logger.info("Consultation flux chargé Vehicle Order : " + query);
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

	public List<VehiculeOrder> findAllByDate(LocalDate date) {
		List<VehiculeOrder> vehicules = new ArrayList<VehiculeOrder>();

		return vehicules;
	}


	private List<Erreur> valider1(List<VehiculeOrder> vehicules, Map<String, Map<String, String>> parametrages) {
		List<Erreur> errors = new ArrayList<>();
		Integer i = 0;
		if (vehicules.isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "La liste générer est vide "));
		}
		for (VehiculeOrder vehicule : vehicules) {
			if (vehicule.getSupacc() == null) {
				errors.add(new Erreur(vehicule.getMagic(), Long.valueOf(i), "supplier n'est pas renseigné"));
			} else if (parametrages.get("supplier") == null
					|| parametrages.get("supplier").get(vehicule.getSupacc()) == null) {
				errors.add(new Erreur(vehicule.getMagic(), Long.valueOf(i), "Le code fournisseur (" + vehicule.getSupacc() + ") n'est pas paramétré "));
			}
			if (vehicule.getBuycurr() == null) {
				errors.add(new Erreur(vehicule.getMagic(), Long.valueOf(i), "currency n'est pas renseigné"));
			} else if (parametrages.get("currency") == null
					|| parametrages.get("currency").get(vehicule.getBuycurr()) == null) {
				errors.add(new Erreur(vehicule.getMagic(), Long.valueOf(i), "currency (" + vehicule.getBuycurr() + ") n'existe pas "));
			}
			if(vehicule.getRecdatee() == null) {
				errors.add(new Erreur(vehicule.getMagic(), Long.valueOf(i), "La date Recdatee est vide"));
			}
		}
		return errors;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<Boolean, Object> simuler(Simulation simulation, LocalDate dateArret) throws CloneNotSupportedException {

		Map<Boolean, Object> resultat = new HashMap<>();

		try {

			List<VehiculeOrder> vehicules = vehiculeOrderRepository.findAllBy(dateArret.atStartOfDay().plusDays(1l).toLocalDate());
			Map<String, Map<String, String>> parametrages = new HashMap<>();
			parametrages.put("supplier", parametrageSupplierRepository.getAll().stream()
					.collect(Collectors.toMap(ParametrageSupplier::getCodeMedtier, ParametrageSupplier::getCodeFinance, (elem1, elem2) -> elem2 )));
			parametrages.put("currency", parametrageRepository.findByCategorie("currency").stream()
					.collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 )));
			List<Erreur> erreurs = valider1(vehicules, parametrages);
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
				List<SimulationVehiculeOrder> vehiculeList = new ArrayList<>();
				Long numero = pieceComptableRepository.getMaxNumeroPieceByCodeJournal(Constantes.FLUX_VEH_ORD);
				int i = 1;
				for (VehiculeOrder vehicule : vehicules) {
					vehiculeList.add(new SimulationVehiculeOrder(vehicule, simulation));
					PieceComptable piece = new PieceComptable();
					piece.setCodeJournale("OD FRS ETR");
					piece.setDate(LocalDate.now());
					piece.setNumeroPiece(numero++);
					piece.setEnteteDoc("Supplier name (" + parametrages.get("supplier").get(vehicule.getSupacc()) + ")");
					piece.setFlux(simulation.getFlux());
					piece.setSimulation(simulation);
					/**
					 * TODO : Corriger la relation entre Piece comptable et VehicleOrder
					 */
					//piece.setVehicule(vehicule);
					EcritureComptable ecritureC = new EcritureComptable();
					ecritureC.setSens('C');
					ecritureC.setAccountDescription("Commandes en cours");
					double freight = ((int)(vehicule.getFreight() * 100)) / 100.0 ;
					// freight.setScale(2);
					double cons = ((int)(vehicule.getCons() * 100)) / 100.0 ;
//				cons.setScale(2);
					double montantCurr =  ((int)((freight + cons) * 100)) / 100.0 ;
					double montantMAD = ((int)(vehicule.getBuyexch() * montantCurr * 100)) / 100.0 ;
					ecritureC.setMontant(montantCurr);
					ecritureC.setSens('C');
					ecritureC.setMontantMAD(montantMAD);
					ecritureC.setPiece(piece);
					ecritureC.setRef2(vehicule.getComm());
					ecritureC.setRef3(vehicule.getVehicle());
					ecritureC.setRef4(vehicule.getRecdatee().format(DateTimeFormatter.BASIC_ISO_DATE));
					ecritureC.setRef1(vehicule.getOrder());
					ecritureC.setRef5(vehicule.getFrandesc());
					ecritureC.setRef6(String.format("1"));
					ecritureC.setNcpt(String.join(".", "441101", parametrages.get("supplier").get(vehicule.getSupacc())));
					EcritureComptable ecritureD = (EcritureComptable) ecritureC.clone();
					ecritureD.setSens('D');
					ecritureD.setNcpt(String.join(".", "348860", parametrages.get("supplier").get(vehicule.getSupacc())));
					piece.getEcritures().add(ecritureC);
					piece.getEcritures().add(ecritureD);
					pieceComptableRepository.save(piece);
					pieces.add(mapper.entityTobean(piece));
				}
				simulationRepository.saveAll(vehiculeList);
				resultat.put(true, pieces);
			}
		} catch (Exception ex) {
			String errDescr = "L'erreur suivante s'est produite lors de la simulation : " + ex.getMessage();
			List<Erreur> erreurs = new ArrayList<>();
			Erreur erreur = new Erreur(null, 0l, errDescr);
			erreur.setSimulation(simulation);
			erreurs.add(erreur);
			//simulation.setErreurs(erreurs);

			Map<String, List<String>> map = new HashMap<>();
			List<String> err = new ArrayList<>();
			err.add(errDescr);
			map.put("0", err);
			resultat.put(false, map);
		}
		return resultat;
	}

	public  List<ma.inetum.brique.model.metier.VehiculeOrder> findAllForBatch(Long firstElementToLoad) {
		var liste = vehiculeOrderMRepository.findAllForBatch(firstElementToLoad);
		return liste;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean saveAll(List<VehiculeOrder> list, Chargement newChargement) {
//		Chargement chg = chargementRepository.getOne(newChargement.getId());
//		for (VehiculeOrder vehiculeOrder : list) {
//			vehiculeOrder.setChargement(chg);
//		}
		vehiculeOrderRepository.saveAll(list);
		return true;
	}
	@Transactional(value = "chainedTransactionManager")
	@Override
	public Boolean generer(Simulation simulation) {
		LocalDateTime now = LocalDateTime.now();
		List<VehiculeOrder> vehicules = simulationRepository.findBySimulation(simulation);
		for (var vehicule : vehicules) {
			vehicule.setDateEnvoi(now);
			vehicule.setSent(true);
		}
		vehiculeOrderRepository.saveAll(vehicules);
		return true;
	}

	@Override
	public void saveAll(List<VehiculeOrder> list) {
		// TODO Auto-generated method stub

	}
}
