package ma.inetum.brique.batch;

import ma.inetum.brique.exception.ExceptionFonctionnelle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class VehicleFinalisationScheduler {
    @Autowired
    private VehicleFinalisationBatchLoader vehicleFinalisationBatchLoader;

    private static final Logger logger = LoggerFactory.getLogger(VehicleFinalisationScheduler.class);

    @Scheduled(cron = "${vehicule.finalisation.cron}")
    public void loadTable() {

        logger.info("Start Chargement flux Vehicle Finalisation");

        try {
            vehicleFinalisationBatchLoader.loadVehicleFinalisation();
        } catch (ExceptionFonctionnelle e) {
            logger.error("Une exception s'est produite lors du chargement du flux Vehicle Finalisation", e);
        }

        logger.info("End Chargement flux Vehicle Finalisation");
    }
}
