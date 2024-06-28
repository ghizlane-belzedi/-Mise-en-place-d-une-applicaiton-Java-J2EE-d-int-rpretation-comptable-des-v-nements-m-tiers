package ma.inetum.brique.batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ma.inetum.brique.exception.ExceptionFonctionnelle;

@Service
public class WorkShopScheduler {

	@Autowired
	private WorkShopBatchLoader workShopBatchLoader;

	private static final Logger logger = LoggerFactory.getLogger(WorkShopScheduler.class);

	@Scheduled(cron = "${workshop.cron}")
	public void loadTable() {

		logger.info("Start Chargement flux workshop");

		try {
			workShopBatchLoader.loadCosting();
		} catch (ExceptionFonctionnelle e) {
			logger.error("Une exception s'est produite lors du chargement du flux workshop", e);
		}

		logger.info("End Chargement flux workshop");
	}

}
