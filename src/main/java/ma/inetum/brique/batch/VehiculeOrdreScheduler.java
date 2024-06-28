package ma.inetum.brique.batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ma.inetum.brique.exception.ExceptionFonctionnelle;

@Service
public class VehiculeOrdreScheduler {

	@Autowired
	private VehicleOrderBatchLoader vehicleOrderBatchLoader;
	
	private static final Logger logger = LoggerFactory.getLogger(VehiculeOrdreScheduler.class);
    
    @Scheduled(cron = "${vehicule.ordre.cron}")
    public void loadTable() {
    	
    	logger.info("Start Chargement flux VehicleOrder");
    	
    	try {
			vehicleOrderBatchLoader.loadVehicleOrder();
		} catch (ExceptionFonctionnelle e) {
			logger.error("Une exception s'est produite lors du chargement du flux VO", e);
		}
    	
    	logger.info("End Chargement flux VehicleOrder");
    }
    
}
