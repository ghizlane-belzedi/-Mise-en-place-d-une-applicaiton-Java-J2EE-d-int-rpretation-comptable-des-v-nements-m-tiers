package ma.inetum.brique.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import ma.inetum.brique.bean.ListString;
import ma.inetum.brique.bean.SimulationDetailsView;
import ma.inetum.brique.bean.SimulationView;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.mapper.SimulationMapper;
import ma.inetum.brique.model.metier.Flux;
import ma.inetum.brique.model.principale.CostingDetails;
import ma.inetum.brique.model.principale.CostingHeader;
import ma.inetum.brique.model.principale.DeliveryP;
import ma.inetum.brique.model.principale.Deposit;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.Invoice;
import ma.inetum.brique.model.principale.LogisticOperationsP;
import ma.inetum.brique.model.principale.ParametrageFluxField;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationState;
import ma.inetum.brique.model.principale.SimulationVehicleFinalisation;
import ma.inetum.brique.model.principale.VehicleFinalisationDetailsP;
import ma.inetum.brique.model.principale.VehicleFinalisationHeaderP;
import ma.inetum.brique.model.principale.VehiculeOrder;
import ma.inetum.brique.model.principale.WorkShopDetails;
import ma.inetum.brique.model.principale.WorkShopHeader;
import ma.inetum.brique.repository.coda.LinkHeadRepository;
import ma.inetum.brique.repository.coda.LinkLineRepository;
import ma.inetum.brique.repository.coda.LinkQuantityRepository;
import ma.inetum.brique.repository.principale.ParametrageFluxRepository;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.repository.principale.SimulationRepository;
import ma.inetum.brique.services.CodaService;
import ma.inetum.brique.services.CostingService;
import ma.inetum.brique.services.DeliveryService;
import ma.inetum.brique.services.DepositService;
import ma.inetum.brique.services.InvoiceService;
import ma.inetum.brique.services.LogisticOperationsService;
import ma.inetum.brique.services.SimulationService;
import ma.inetum.brique.services.VehicleFinalisationService;
import ma.inetum.brique.services.VehiculeOrderService;
import ma.inetum.brique.services.WorkShopService;
import ma.inetum.brique.utilities.Constantes;
import ma.inetum.brique.utilities.NumeroPieceService;

@Service
public class SimulationServiceImpl implements SimulationService {

	private static final Logger log = LoggerFactory.getLogger(SimulationServiceImpl.class);
	@Autowired
	private SimulationRepository simulationRepository;
	@Autowired
	private ParametrageFluxRepository fluxRepository;

	@Autowired
	private VehiculeOrderService vehiculeOrderService;

	@Autowired
	private VehicleFinalisationService vehicleFinalisationService;

	@Autowired
	private CostingService costingService;
	@Autowired
	private WorkShopService workShopService;
	@Autowired
	private DepositService depositService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private LogisticOperationsService logisticOperationsService;
	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private NumeroPieceService numeroPieceService;
	@Autowired
	private PieceComptableRepository pieceComptableRepository;
	@Autowired
	LinkHeadRepository headRepository;
	@Autowired
	LinkLineRepository lineRepository;
	@Autowired
	LinkQuantityRepository quantityRepository;
	@Autowired
	CodaService codaService;
	private SimulationMapper mapper = SimulationMapper.getInstance();


	@Override
	public List<SimulationView> findByCriteria(String flux, String state, int page, int size, String sort, String dir) {
		return null;
	}

	@Override
	public List<SimulationView> getAll() {
		return simulationRepository.findAll().stream().map(e -> mapper.simulationToSimulationView(e)).collect(Collectors.toList());
	}

	@Override
	public SimulationDetailsView findById(Long id) {
		if (id == null || id == 0)
			throw new RuntimeException("Identifiant absent");
		Simulation simulation = simulationRepository.findById(id).orElse(null);
		SimulationDetailsView s = null;
		ListString resultat = null;
		if (simulation != null) {
			s = mapper.simulationToSimulationDetailsView(simulation);
			s.setEnable(simulationRepository.isNotEnabled(SimulationState.ANNULER, SimulationState.VALIDER,
					simulation.getFlux().getCode()) == 0);
			s.setResultatGeneration(generer(simulation.getFlux().getCode(), id));
		} else {
			throw new RuntimeException("Aucune Simulation trouvé");
		}
		return s;
	}
	private ListString generer(String flux, Long simulationId) {
		ListString details = new ListString();
		Stream<ParametrageFluxField> fields = fluxRepository.findByCode(flux).getFields().stream()
				.filter(e -> e.getVisible());
		// details.setHeader(fields.map(e -> e.getDescription()).toList());
		List<String> header = new LinkedList<>();
		List<String> dbField = new LinkedList<>();
		/***
		 * Faut voir comment gérer les alias en double dans la requêtes
		 */
		fields.sorted((e1, e2) -> {
			if (!e1.getVisible() && !e2.getVisible())
				return 0;
			if (!e2.getVisible())
				return -1;
			if (!e1.getVisible())
				return 1;
			return -1 * (e2.getOrdre() - e1.getOrdre());
		}).forEachOrdered(e -> {
			header.add(e.getDescription());

			if (e.getCodeBDD().split("\\.").length ==  2) {
				dbField.add(e.getCodeBDD().split("\\.")[0] + ".\"" + e.getCodeBDD().split("\\.")[1] + "\" AS \"" + e.getId() + "\"");				
			} else {
				dbField.add("\"" + e.getCodeBDD() + "\" AS \"" + e.getId() + "\"");
			}
		});
		details.setHeader(header);
		if(Constantes.FLUX_VEH_FIN.equalsIgnoreCase(flux)){
			details.setDetails(vehicleFinalisationService.generer(dbField, simulationId, VehicleFinalisationHeaderP.class, VehicleFinalisationDetailsP.class));
		} else if (Constantes.FLUX_COSTING.equalsIgnoreCase(flux)) {
			details.setDetails(costingService.generer(dbField, simulationId, CostingHeader.class, CostingDetails.class));
		} else if (Constantes.FLUX_WORKSHOP.equalsIgnoreCase(flux)) {
			details.setDetails(workShopService.generer(dbField, simulationId, WorkShopHeader.class, WorkShopDetails.class));
		} else if(Constantes.FLUX_Logistic_Operations.equalsIgnoreCase(flux)){
			details.setDetails(logisticOperationsService.generer(dbField, simulationId, LogisticOperationsP.class));
		} else if (Constantes.FLUX_DEPOSIT.equalsIgnoreCase(flux)) {
			details.setDetails(depositService.generer(dbField, simulationId, Deposit.class));
		} else if (Constantes.FLUX_INVOICE.equalsIgnoreCase(flux)) {
			details.setDetails(invoiceService.generer(dbField, simulationId, Invoice.class));
		} else if(Constantes.FLUX_DELIVERY.equalsIgnoreCase(flux)){
			log.info("dbField:" + (new Gson()).toJson(dbField));
			details.setDetails(deliveryService.generer(dbField, simulationId, DeliveryP.class));
		}


		return details;
	}
	@Override
	public ListString generer(String flux, LocalDate dateArret) {
		ListString details = new ListString();
		Stream<ParametrageFluxField> fields = fluxRepository.findByCode(flux).getFields().stream()
				.filter(e -> e.getVisible());
		// details.setHeader(fields.map(e -> e.getDescription()).toList());
		List<String> header = new LinkedList<>();
		List<String> dbField = new LinkedList<>();
		/***
		 * Faut voir comment gérer les alias en double dans la requêtes
		 */
		fields.sorted((e1, e2) -> {
			if (!e1.getVisible() && !e2.getVisible())
				return 0;
			if (!e2.getVisible())
				return -1;
			if (!e1.getVisible())
				return 1;
			return -1 * (e2.getOrdre() - e1.getOrdre());
		}).forEachOrdered(e -> {
			header.add(e.getDescription());

			if (e.getCodeBDD().split("\\.").length ==  2) {
				dbField.add(e.getCodeBDD().split("\\.")[0] + ".\"" + e.getCodeBDD().split("\\.")[1] + "\" AS \"" + e.getId() + "\"");				
			} else {
				dbField.add("\"" + e.getCodeBDD() + "\" AS \"" + e.getId() + "\"");
			}
		});
		details.setHeader(header);
		if (Constantes.FLUX_VEH_ORD.equalsIgnoreCase(flux)) {
			details.setDetails(vehiculeOrderService.generer(dbField, dateArret, VehiculeOrder.class));
		} else if(Constantes.FLUX_VEH_FIN.equalsIgnoreCase(flux)){
			details.setDetails(vehicleFinalisationService.generer(dbField, dateArret, VehicleFinalisationHeaderP.class, VehicleFinalisationDetailsP.class));
		} else if (Constantes.FLUX_COSTING.equalsIgnoreCase(flux)) {
			details.setDetails(costingService.generer(dbField, dateArret, CostingHeader.class, CostingDetails.class));
		} else if (Constantes.FLUX_WORKSHOP.equalsIgnoreCase(flux)) {
			details.setDetails(workShopService.generer(dbField, dateArret, WorkShopHeader.class, WorkShopDetails.class));
		} else if(Constantes.FLUX_Logistic_Operations.equalsIgnoreCase(flux)){
			details.setDetails(logisticOperationsService.generer(dbField, dateArret, LogisticOperationsP.class));
		} else if (Constantes.FLUX_DEPOSIT.equalsIgnoreCase(flux)) {
			details.setDetails(depositService.generer(dbField, dateArret, Deposit.class));
		} else if (Constantes.FLUX_INVOICE.equalsIgnoreCase(flux)) {
			details.setDetails(invoiceService.generer(dbField, dateArret, Invoice.class));
		} else if(Constantes.FLUX_DELIVERY.equalsIgnoreCase(flux)){
			log.info("dbField:" + (new Gson()).toJson(dbField));
			details.setDetails(deliveryService.generer(dbField, dateArret, DeliveryP.class));
		}


		return details;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private Simulation initSimulation(String flux, String dateArret) {
		Simulation simulation = new Simulation();
		simulation.setDateArreter(LocalDate.parse(dateArret));
		simulation.setDateGeneration(LocalDateTime.now());
		simulation.setDateSimuation(LocalDateTime.now());
		simulation.setFlux(fluxRepository.findByCode(flux));
		simulation.setState(SimulationState.EN_COURS);
		var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user instanceof UserDetails) {
			simulation.setUsername(((UserDetails) user).getUsername());
		} else {
			simulation.setUsername(user.toString());
		}
		simulation = simulationRepository.save(simulation);
		return simulation;
	}
	
	
	public Map<Boolean, Object> simuler(String flux, String dateArret, SimulationDetailsView view) throws CloneNotSupportedException {
		view.setFlux(flux);
		view.setDateArreter(dateArret);
		Map<Boolean, Object> resultat = new HashMap<>();
		
		Simulation simulation = initSimulation(flux, dateArret);
		
		try {
			
			if (Constantes.FLUX_VEH_ORD.equalsIgnoreCase(flux)) {
				resultat = vehiculeOrderService.simuler(simulation, LocalDate.parse(dateArret));
			}else if(Constantes.FLUX_VEH_FIN.equalsIgnoreCase(flux)){
				resultat = vehicleFinalisationService.simuler(simulation, LocalDate.parse(dateArret));
			} else if (Constantes.FLUX_COSTING.equalsIgnoreCase(flux)) {
				resultat = costingService.simuler(simulation, LocalDate.parse(dateArret));
			} else if (Constantes.FLUX_WORKSHOP.equalsIgnoreCase(flux)) {
				resultat = workShopService.simuler(simulation, LocalDate.parse(dateArret));
			} else if(Constantes.FLUX_Logistic_Operations.equalsIgnoreCase(flux)){
				resultat = logisticOperationsService.simuler(simulation, LocalDate.parse(dateArret));
			} else if (Constantes.FLUX_DEPOSIT.equalsIgnoreCase(flux)) {
				resultat = depositService.simuler(simulation, LocalDate.parse(dateArret));
			} else if (Constantes.FLUX_INVOICE.equalsIgnoreCase(flux)) {
				resultat = invoiceService.simuler(simulation, LocalDate.parse(dateArret));
			} else if(Constantes.FLUX_DELIVERY.equalsIgnoreCase(flux)){
				resultat = deliveryService.simuler(simulation, LocalDate.parse(dateArret));
			}
			if (resultat.get(false) != null) {
				simulation.setState(SimulationState.WITH_ERRORS);
			} else if (resultat.get(true) != null) {
				simulation.setState(SimulationState.EN_INSTANCE);
			}
			simulation = simulationRepository.save(simulation);
			view.setId(simulation.getId());
		} catch (Exception e) {
			log.error("Une erreur s'est produite lors de la simulation", e);
			if (simulation != null && simulation.getId() != null) {
				simulation.setState(SimulationState.WITH_ERRORS);
				Erreur err = new Erreur(null, Long.valueOf(0), "Une erreur technique s'est produite lors de la simulation. Merci de contacter votre administrateur (" + e.getMessage() + ")");
				err.setSimulation(simulation);
				simulation.getErreurs().add(err);
				simulation.setPieces(null);
				simulation = simulationRepository.save(simulation);
			}
		}
		return resultat;
	}

	@Override
	@Transactional//(value = "chainedTransactionManager") //, rollbackFor = ExceptionFonctionnelle.class)
	public SimulationDetailsView generer(Long id) throws ExceptionFonctionnelle {
		try {
			SimulationDetailsView view = new SimulationDetailsView();
			Optional<Simulation> simulation_ = simulationRepository.findById(id);
			if (simulation_.isEmpty()) {
				view.setComment("L'identifiant '" + id + "' ne correspand a aucune simulation");
				return view;
			}
			var simulation = simulation_.get();
			view = mapper.simulationToSimulationDetailsView(simulation);
			if (simulation_.get().getPieces().size() == 0) {
				view.setComment("La simulation ne contient aucune piece comptable");
				return view;
			}
			simulation = simulation_.get();
			view = mapper.simulationToSimulationDetailsView(simulation);
			if (simulation_.get().getState() == SimulationState.ANNULER) {
				view.setComment("La simulation est déja annulée");
			} else if (simulation_.get().getState() == SimulationState.EN_COURS) {
				view.setComment("La simulation n'est pas encore terminer");
			} else if (simulation_.get().getState() == SimulationState.VALIDER) {
				view.setComment("La simulation a déja été validé");
			} else if (simulation_.get().getState() == SimulationState.WITH_ERRORS) {
				view.setComment(
						"La simulation s'est achevé avec des erreurs, merci de corriger les erreurs et de lancer une nouvelle simulation");
			} else {
				boolean resultat = false;
				if (Constantes.FLUX_VEH_ORD.equalsIgnoreCase(simulation.getFlux().getCode())) {
					resultat = vehiculeOrderService.generer(simulation);
				} else if (Constantes.FLUX_VEH_FIN.equalsIgnoreCase(simulation.getFlux().getCode())){
					resultat = vehicleFinalisationService.generer(simulation);
				} else if (Constantes.FLUX_COSTING.equalsIgnoreCase(simulation.getFlux().getCode())) {
					resultat = costingService.generer(simulation);
				} else if (Constantes.FLUX_WORKSHOP.equalsIgnoreCase(simulation.getFlux().getCode())) {
					resultat = workShopService.generer(simulation);
				} else if (Constantes.FLUX_Logistic_Operations.equalsIgnoreCase(simulation.getFlux().getCode())) {
					resultat = logisticOperationsService.generer(simulation);
				} else if (Constantes.FLUX_DEPOSIT.equalsIgnoreCase(simulation.getFlux().getCode())) {
					resultat = depositService.generer(simulation);
				} else if (Constantes.FLUX_INVOICE.equalsIgnoreCase(simulation.getFlux().getCode())) {
					resultat = invoiceService.generer(simulation);
				} else if (Constantes.FLUX_DELIVERY.equalsIgnoreCase(simulation.getFlux().getCode())) {
					resultat = deliveryService.generer(simulation);
				}
				if(resultat) {
					Map<String, Long> mapNumeroPieces = new HashMap<>();
					for(var piece : simulation.getPieces())
					{
						if(mapNumeroPieces.get(piece.getCodeJournale()) == null ) {
							Long lastnumer = numeroPieceService.getNumeroPiece(piece.getCodeJournale());
							mapNumeroPieces.put(piece.getCodeJournale(), lastnumer);
						} else {
							mapNumeroPieces.put(piece.getCodeJournale(), mapNumeroPieces.get(piece.getCodeJournale()) + 1l );
						}
						piece.setNumeroPiece(mapNumeroPieces.get(piece.getCodeJournale()));
						piece.setSent(true);
						//piece.setDate(LocalDate.now());
						
						codaService.saveInCoda(piece);
					}
					simulation.setDateGeneration(LocalDateTime.now());
					var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if (user instanceof UserDetails) {
						simulation.setUsername(((UserDetails) user).getUsername());
					} else {
						simulation.setUsername(user.toString());
					}
					simulation.setState(SimulationState.VALIDER);
					simulationRepository.save(simulation);
				} else {
					view.setComment("Une erreur est survenu lors de la génération");
				}	
			}
			
			return view;
		} catch (Exception e) {
			String msg = "L'exception suivante a été levée lors de la génération vers CODA : " + e.getMessage();
			log.error(msg, e);
			throw new RuntimeException(msg, e);
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public SimulationDetailsView annuler(Long id) {
		SimulationDetailsView view = new SimulationDetailsView();
		Optional<Simulation> simulation_ = simulationRepository.findById(id);
		if (simulation_.isEmpty()) {
			view.setComment("La simulation ne contient aucune piece comptable");
			return view;
		} else {
			var simulation = simulation_.get();
			view = mapper.simulationToSimulationDetailsView(simulation);
			if (simulation_.get().getState() == SimulationState.ANNULER) {
				view.setComment("La simulation est déja annulée");
			} else if (simulation_.get().getState() == SimulationState.VALIDER) {
				view.setComment("La simulation a déja été validé");
			}
			simulation.setState(SimulationState.ANNULER);
			var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (user instanceof UserDetails) {
				simulation.setUsername(((UserDetails) user).getUsername());
			} else {
				simulation.setUsername(user.toString());
			}
			simulationRepository.save(simulation);
		}
		return view;
	}
	@Override
	public DataTablesOutput<SimulationView> getAll(DataTablesInput input, List<String> flux) {
		DataTablesOutput<SimulationView> view = new DataTablesOutput<>();
		String[] search = input.getSearch().getValue().split(";");
		Pageable pageable = PageRequest.of(input.getStart() / input.getLength(), input.getLength(), Direction.fromOptionalString(input.getOrder().get(0).getDir()).orElse(Direction.DESC), input.getColumns().get(input.getOrder().get(0).getColumn() ).getName());
		Specification<Simulation> specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (search.length > 0 && search[0] != null && !search[0].isBlank()) {	
				predicates.add(criteriaBuilder.like(root.join("flux").get("code"), search[0]));
			}
			try {
				if(search.length > 1 && search[1] != null && !search[1].isBlank())
					predicates.add(criteriaBuilder.equal(root.get("state"), SimulationState.valueOf(search[1])));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			if (search.length > 2 && search[2] != null && !search[2].isBlank()) {
				try {
					LocalDateTime dateDebut = LocalDate.parse(search[2]).atStartOfDay();
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateSimuation"), dateDebut));	
				} catch (Exception e) {
					log.error("Date debut n'est pas au format correct");
				}
			}
			if (search.length > 3 && search[3] != null && !search[3].isBlank()) {
				try {
					LocalDateTime dateFin = LocalDate.parse(search[3]).atStartOfDay();
					predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateSimuation"), dateFin));	
				} catch (Exception e) {
					log.error("Date fin n'est pas au format correct");
				}
			}
			predicates.add( root.join("flux").get("code").in(flux));
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};
		Page<Simulation> simulations = simulationRepository.findAll(specification, pageable);
		
		view.setData(simulations.stream().map(e -> mapper.simulationToSimulationView(e)).collect(Collectors.toList()));
		view.setDraw(input.getDraw());
		view.setError(null);
		view.setRecordsFiltered(simulationRepository.count(specification));
		view.setRecordsTotal(simulationRepository.count());
		return view;
	} 
	
	public List<PieceComptable> getAllPieces(Long simulationId) {
		List<PieceComptable> pieces = pieceComptableRepository.findbySimulationId(simulationId);
		return pieces != null ? pieces : new ArrayList<>();
	}
}
