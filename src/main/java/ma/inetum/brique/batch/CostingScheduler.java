package ma.inetum.brique.batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ma.inetum.brique.exception.ExceptionFonctionnelle;

@Service
public class CostingScheduler {

	@Autowired
	private CostingBatchLoader costingBatchLoader;
	
	private static final Logger logger = LoggerFactory.getLogger(CostingScheduler.class);
    
    @Scheduled(cron = "${costing.cron}")
    public void loadTable() {
    	
    	logger.info("Start Chargement flux costing");
    	
    	try {
    		costingBatchLoader.loadCosting();
		} catch (ExceptionFonctionnelle e) {
			logger.error("Une exception s'est produite lors du chargement du flux COSTING", e);
		}
    	
    	logger.info("End Chargement flux COSTING");
    }
    
}
