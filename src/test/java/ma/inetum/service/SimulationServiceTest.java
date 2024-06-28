//package ma.inetum.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import ma.inetum.brique.bean.SimulationView;
//import ma.inetum.brique.mapper.SimulationMapper;
//import ma.inetum.brique.model.principale.Simulation;
//import ma.inetum.brique.model.principale.VehiculeOrder;
//import ma.inetum.brique.repository.metier.VehiculeOrderRepository;
//import ma.inetum.brique.repository.principale.ParametrageFluxRepository;
//import ma.inetum.brique.repository.principale.SimulationRepository;
//import ma.inetum.brique.repository.principale.VehiculeOrderPRepository;
//import ma.inetum.brique.services.CostingService;
//import ma.inetum.brique.services.DeliveryService;
//import ma.inetum.brique.services.DepositService;
//import ma.inetum.brique.services.InvoiceService;
//import ma.inetum.brique.services.LogisticOperationsService;
//import ma.inetum.brique.services.SimulationService;
//import ma.inetum.brique.services.VehicleFinalisationService;
//import ma.inetum.brique.services.VehiculeOrderService;
//import ma.inetum.brique.services.WorkShopService;
//import ma.inetum.brique.services.impl.SimulationServiceImpl;
//
//@SpringBootTest(classes = SimulationServiceImpl.class)
//public class SimulationServiceTest {
//	@Autowired
//	private SimulationService simulationService;
//
//	@MockBean
//	private SimulationRepository simulationRepository;
//	@MockBean
//	private ParametrageFluxRepository fluxRepository;
//
//	@MockBean
//	private VehiculeOrderService vehiculeOrderService;
//
//	@MockBean
//	private VehicleFinalisationService vehicleFinalisationService;
//	@MockBean
//	private VehiculeOrderRepository vehiculeOrderRepository;
//	@MockBean
//	private CostingService costingService;
//	@MockBean
//	private WorkShopService workShopService;
//	@MockBean
//	private DepositService depositService;
//	@MockBean
//	private InvoiceService invoiceService;
//	@MockBean
//	private LogisticOperationsService logisticOperationsService;
//	@MockBean
//	private DeliveryService deliveryService;
//	@MockBean
//	private VehiculeOrderPRepository vehiculeOrderPRepository;
//	
//	private SimulationMapper mapper = SimulationMapper.getInstance();
//
//	@Test
//	public void getAll() {
//		Mockito.when(simulationRepository.findAll()).thenReturn(mockGetALl());
//		List<SimulationView> list = simulationService.getAll();
//		Assertions.assertEquals(list.size(), 0);
//	}
//	
//	
//	
//	private List<Simulation> mockGetALl() {
//		List<Simulation> list = new ArrayList<>();
//		
//		return list;
//	}
//	
//	
//	
//	
//	private List<VehiculeOrder> getAllVehiculeOrders(){
//		List<VehiculeOrder> liste = new ArrayList<>();
//		VehiculeOrder vehicule = null; 
//		vehicule = new VehiculeOrder();
//		
//		return liste;
//	}
//}
