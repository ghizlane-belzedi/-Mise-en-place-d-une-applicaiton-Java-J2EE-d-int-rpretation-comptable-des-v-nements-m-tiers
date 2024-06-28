package ma.inetum.brique.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import ma.inetum.brique.batch.CostingBatchLoader;
import ma.inetum.brique.batch.DeliveryBatchLoader;
import ma.inetum.brique.batch.DepositBatchLoader;
import ma.inetum.brique.batch.InvoiceBatchLoader;
import ma.inetum.brique.batch.LogisticOperationsBatchLoader;
import ma.inetum.brique.batch.VehicleFinalisationBatchLoader;
import ma.inetum.brique.batch.VehicleOrderBatchLoader;
import ma.inetum.brique.batch.WorkShopBatchLoader;
import ma.inetum.brique.bean.ChargementDetailsView;
import ma.inetum.brique.bean.ChargementView;
import ma.inetum.brique.bean.ListString;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.mapper.ChargementMapper;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.CostingDetails;
import ma.inetum.brique.model.principale.CostingHeader;
import ma.inetum.brique.model.principale.DeliveryP;
import ma.inetum.brique.model.principale.Deposit;
import ma.inetum.brique.model.principale.Invoice;
import ma.inetum.brique.model.principale.LogisticOperationsP;
import ma.inetum.brique.model.principale.ParametrageFLux;
import ma.inetum.brique.model.principale.ParametrageFluxField;
import ma.inetum.brique.model.principale.VehicleFinalisationDetailsP;
import ma.inetum.brique.model.principale.VehicleFinalisationHeaderP;
import ma.inetum.brique.model.principale.VehiculeOrder;
import ma.inetum.brique.model.principale.WorkShopDetails;
import ma.inetum.brique.model.principale.WorkShopHeader;
import ma.inetum.brique.repository.principale.ChargementRepository;
import ma.inetum.brique.repository.principale.ParametrageFluxRepository;
import ma.inetum.brique.services.ChargementService;
import ma.inetum.brique.services.CostingService;
import ma.inetum.brique.services.DeliveryService;
import ma.inetum.brique.services.DepositService;
import ma.inetum.brique.services.InvoiceService;
import ma.inetum.brique.services.LogisticOperationsService;
import ma.inetum.brique.services.VehicleFinalisationService;
import ma.inetum.brique.services.VehiculeOrderService;
import ma.inetum.brique.services.WorkShopService;
import ma.inetum.brique.utilities.Constantes;

@Service
@Transactional
public class ChargementServiceImpl implements ChargementService {

	private final static Logger logger = LoggerFactory.getLogger(ChargementServiceImpl.class);
	@Autowired
	ChargementRepository chargementRepository;

	@Autowired
	VehiculeOrderService vehiculeOrderService;

	@Autowired
	VehicleFinalisationService vehicleFinalisationService;
	@Autowired
	CostingService costingService;
	@Autowired
	WorkShopService workShopService;
	@Autowired
	LogisticOperationsService logisticOperationsService;
	@Autowired
	DepositService depositService;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	DeliveryService deliveryService;
	@Autowired
	ParametrageFluxRepository paramFluxRepository;

	/**
	 * BATCH
	 */
	@Autowired
	CostingBatchLoader costingBatchLoader;
	@Autowired
	DeliveryBatchLoader deliveryBatchLoader;
	@Autowired
	DepositBatchLoader depositBatchLoader;
	@Autowired
	InvoiceBatchLoader invoiceBatchLoader;
	@Autowired
	LogisticOperationsBatchLoader logisticBatchLoader;
	@Autowired
	VehicleFinalisationBatchLoader vehicleFinalisationBatchLoader;
	@Autowired
	VehicleOrderBatchLoader vehicleOrderBatchLoader;
	@Autowired
	WorkShopBatchLoader workShopBatchLoader;
	ChargementMapper mapper = ChargementMapper.getInstance();

	@Override
	public List<ChargementView> getAll() {
		return chargementRepository.findAll().stream().map(e -> mapper.chargementToChargementView(e)).collect(Collectors.toList());
	}

	@Override
	public ChargementDetailsView findById(Long id) {
		if (id == null || id == 0)
			throw new RuntimeException("Identifiant absent");
		ListString details = new ListString();

		Chargement chargement = chargementRepository.findById(id).orElse(null);
		ChargementDetailsView c = null;
		Stream<ParametrageFluxField> fields = chargement.getFlux().getFields().stream().filter(e -> e.getVisible());
		List<String> header = new LinkedList<>();
		List<String> dbField = new LinkedList<>();
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
		logger.info("dbField : " + (new Gson()).toJson(dbField));
		c = mapper.chargementToChargementDetailsView(chargement);
		if (ChargementState.TERMINER_SUCCESS == chargement.getState()) {
			if (Constantes.FLUX_VEH_ORD.equalsIgnoreCase(chargement.getFlux().getCode())) {
				details.setDetails(vehiculeOrderService.generer(dbField, chargement.getFisrt(), chargement.getLast(),
						VehiculeOrder.class));
			}
			else if(Constantes.FLUX_VEH_FIN.equals(chargement.getFlux().getCode())) {
				details.setDetails(vehicleFinalisationService.generer(dbField, chargement.getFisrt(), chargement.getLast(), VehicleFinalisationHeaderP.class, VehicleFinalisationDetailsP.class));
			}
			else if(Constantes.FLUX_COSTING.equalsIgnoreCase(chargement.getFlux().getCode())) {
				details.setDetails(costingService.generer(dbField, chargement.getFisrt(), chargement.getLast(),
						CostingHeader.class, CostingDetails.class));
			}
			else if(Constantes.FLUX_WORKSHOP.equalsIgnoreCase(chargement.getFlux().getCode())) {
				details.setDetails(workShopService.generer(dbField, chargement.getFisrt(), chargement.getLast(),
						WorkShopHeader.class, WorkShopDetails.class));
			}
			else if(Constantes.FLUX_Logistic_Operations.equals(chargement.getFlux().getCode())){
				details.setDetails(logisticOperationsService.generer(dbField, chargement.getFisrt(), chargement.getLast(), LogisticOperationsP.class));
			}
			else if(Constantes.FLUX_DEPOSIT.equalsIgnoreCase(chargement.getFlux().getCode())) {
				details.setDetails(depositService.generer(dbField, chargement.getFisrt(), chargement.getLast(),
						Deposit.class));
			}
			else if (Constantes.FLUX_INVOICE.equalsIgnoreCase(chargement.getFlux().getCode())) {
				details.setDetails(invoiceService.generer(dbField, chargement.getFisrt(), chargement.getLast(),
						Invoice.class));
			}
			else if(Constantes.FLUX_DELIVERY.equalsIgnoreCase(chargement.getFlux().getCode())){
				details.setDetails(deliveryService.generer(dbField, chargement.getFisrt(), chargement.getLast(), DeliveryP.class));
			}
			c.setDetails(details);
		} else if(ChargementState.WITH_ERRORS == chargement.getState()) {
			c.setStatut(false);
		}
		c.setTotalCharged(0l+ details.getDetails().size());
		return c;
	}

	public Chargement findLastChargementByFlux(ParametrageFLux flux, Pageable page) {
		Chargement result = null;
		Page<Chargement> chargementsPage = chargementRepository.findLastChargementByFlux(flux, page);
		if ((chargementsPage != null) && (chargementsPage.getTotalElements()==1)) {
			result = chargementsPage.getContent().get(0);
		}
		return result;
	}

	public Chargement getOne(Long lastChargementId) {
		return chargementRepository.getOne(lastChargementId);
	}

	public Chargement findLastChargementIdByFluxAndChargementState(ParametrageFLux flux, ChargementState state) {
		Chargement chargement = null;
		Page<Chargement> lastChargementP = chargementRepository.findLastChargementIdByFluxAndState(flux, ChargementState.TERMINER_SUCCESS, PageRequest.of(0, 1, Direction.DESC, "id"));
		if ((lastChargementP != null) && (lastChargementP.getContent()!=null) && (!lastChargementP.getContent().isEmpty())) {
			chargement = lastChargementP.getContent().get(0);
		}
		return chargement;
	}
	public Chargement updateChargementFirstElement(Chargement chargement, Long firstElement) throws ExceptionFonctionnelle {
		Chargement chg = null;
		if ((chargement != null) && (chargement.getId() != null)) {
			chg = chargementRepository.getOne(chargement.getId());
			if (chg != null) {
				chg.setFisrt(firstElement);
				chargementRepository.save(chg);
			} else {
				throw new ExceptionFonctionnelle(
						"Chargement à mettre à jour introuvable Id = '" + chargement.getId() + "'");
			}
		} else {
			throw new ExceptionFonctionnelle("Chargement à mettre à jour ne peut être vide ni son identifiant ");
		}
		return chg;
	}


	public Chargement initChargement(ParametrageFLux flux, Long firstElementToLoad) throws ExceptionFonctionnelle {
		Chargement chargement = null;

		synchronized (this) {
			Page<Chargement> lastChargementP = chargementRepository.findLastChargementByFlux(flux, PageRequest.of(0, 1, Direction.DESC, "id"));

			if ((lastChargementP != null) && (lastChargementP.getContent()!=null) && (!lastChargementP.getContent().isEmpty()) &&
					(lastChargementP.getContent().get(0).getState() == ChargementState.EN_COURS)) {
				throw new ExceptionFonctionnelle(
						"Un chargement pour ce même flux '" + flux.getCode() + "' est toujours en cours");
			} else {
				LocalDateTime now = LocalDateTime.now();
				chargement = new Chargement();
				chargement.setDateChargement(now);
				chargement.setFisrt(firstElementToLoad);
				chargement.setLast(0l);
				chargement.setFlux(flux);
				chargement.setState(ChargementState.EN_COURS);
				chargement = chargementRepository.save(chargement);
			}
		}
		return chargement;
	}

	public Chargement updateChargementStatus(Chargement chargement, ChargementState state, Long lastMagic)
			throws ExceptionFonctionnelle {
		Chargement chg = null;
		if ((chargement != null) && (chargement.getId() != null)) {
			chg = chargementRepository.getOne(chargement.getId());
			if (chg != null) {
				chg.setState(state);
				chg.setLast(lastMagic);
				chg.setDateFinChargement(LocalDateTime.now());
				chargementRepository.save(chg);
			} else {
				throw new ExceptionFonctionnelle(
						"Chargement à mettre à jour introuvable Id = '" + chargement.getId() + "'");
			}
		} else {
			throw new ExceptionFonctionnelle("Chargement à mettre à jour ne peut être vide ni son identifiant ");
		}
		return chg;
	}

	@Override
	public DataTablesOutput<ChargementView> getAll(DataTablesInput input, List<String> flux) {
		DataTablesOutput<ChargementView> view = new DataTablesOutput<>();
		String[] search = input.getSearch().getValue().split(";");
		Pageable pageable = null;
		if(input.getLength() > 0 ) {
			pageable = PageRequest.of(input.getStart() / input.getLength(), input.getLength(), Direction.fromOptionalString(input.getOrder().get(0).getDir()).orElse(Direction.DESC), input.getColumns().get(input.getOrder().get(0).getColumn() ).getName());
		} else {
			pageable = PageRequest.of(0, Integer.MAX_VALUE, Direction.fromOptionalString(input.getOrder().get(0).getDir()).orElse(Direction.DESC), input.getColumns().get(input.getOrder().get(0).getColumn() ).getName());
		}
		Specification<Chargement> specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (search.length > 0 && search[0] != null && !search[0].isBlank()) {
				predicates.add(criteriaBuilder.like(root.join("flux").get("code"), search[0]));
			}
			try {
				if(search.length > 1 && search[1] != null && !search[1].isBlank())
					predicates.add(criteriaBuilder.equal(root.get("state"), ChargementState.valueOf(search[1])));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (search.length > 2 && search[2] != null && !search[2].isBlank()) {
				try {
					LocalDateTime dateDebut = LocalDate.parse(search[2]).atStartOfDay();
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateChargement"), dateDebut));
				} catch (Exception e) {
					logger.error("Date debut n'est pas au format correct");
				}
			}
			if (search.length > 3 && search[3] != null && !search[3].isBlank()) {
				try {
					LocalDateTime dateFin = LocalDate.parse(search[3]).atStartOfDay();
					predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateChargement"), dateFin));
				} catch (Exception e) {
					logger.error("Date fin n'est pas au format correct");
				}
			}
			predicates.add( root.join("flux").get("code").in(flux));
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};
		Page<Chargement> chargements = chargementRepository.findAll(specification, pageable);

		view.setData(chargements.stream().map(e -> mapper.chargementToChargementView(e)).collect(Collectors.toList()));
		view.setDraw(input.getDraw());
		view.setError(null);
		view.setRecordsFiltered(chargementRepository.count(specification));
		view.setRecordsTotal(chargementRepository.count());
		return view;
	}
	public List<ChargementView> situationChargement() {
		var chargements = chargementRepository.findAll();
		List<ChargementView> liste = new ArrayList<>();
		
		return chargements.stream().map( e -> mapper.chargementToChargementView(e)).collect(Collectors.toList());
	}
	public String launch( String f, List<String> flux) {
		String result = "";
		if(f == null || f.isBlank()) {
			result = "Flux absent";
		} else {
			if(flux == null || !flux.contains(f)) {
				result = "Vous n'avez pas le droit de consulter ce chargement";
			} else {
				var id = chargementRepository.findLastId(f);
				id = id != null ? id : 0l;
				Optional<Chargement> chargement = chargementRepository.findById(id);
				if(chargement != null && !chargement.isEmpty() && chargement.get().getState() == ChargementState.EN_COURS) {
					result = "Vous n'avez pas le droit de lancer un nouveau chargement. Un chargement pour le flux " + f + " est en cours";
				} if (Constantes.FLUX_DEPOSIT.equals(f) && (!chargement.isEmpty())) {
					Chargement chg = chargement.get();
					if ((ChargementState.TERMINER_SUCCESS == chg.getState()) && (LocalDate.now().compareTo(chg.getDateChargement().toLocalDate())==0)) {
						result = "Un chargement pour le flux " + f + " a été déjà lancé aujourd'hui avec success";
					}
				} else {
					result = "OK";
					Runnable runnable = new Runnable() {
						public void run() {
							try {
								if (Constantes.FLUX_VEH_ORD.equalsIgnoreCase(f)) {
									vehicleOrderBatchLoader.loadVehicleOrder();
								} else if (Constantes.FLUX_VEH_FIN.equalsIgnoreCase(f)) {
									vehicleFinalisationBatchLoader.loadVehicleFinalisation();
								} else if (Constantes.FLUX_COSTING.equalsIgnoreCase(f)) {
									costingBatchLoader.loadCosting();
								} else if (Constantes.FLUX_WORKSHOP.equalsIgnoreCase(f)) {
									workShopBatchLoader.loadCosting();
								} else if (Constantes.FLUX_Logistic_Operations.equalsIgnoreCase(f)) {
									logisticBatchLoader.loadLogisticOperations();
								} else if (Constantes.FLUX_DEPOSIT.equalsIgnoreCase(f)) {
									depositBatchLoader.loadDeposits();
								} else if (Constantes.FLUX_INVOICE.equalsIgnoreCase(f)) {
									invoiceBatchLoader.loadInvoice();
								} else if (Constantes.FLUX_DELIVERY.equalsIgnoreCase(f)) {
									deliveryBatchLoader.loadDelivery();
								}
							} catch (ExceptionFonctionnelle e) {
								logger.error(e.getMessage());
							}
						}
					};
					new Thread(runnable).start();
				}
			}
		}
		return result;
	}
}
