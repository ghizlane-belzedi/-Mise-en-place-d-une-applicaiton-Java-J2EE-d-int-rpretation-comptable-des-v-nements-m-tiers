package ma.inetum.brique.batch;

import ma.inetum.brique.exception.ExceptionFonctionnelle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LogisticOperationsScheduler {
    @Autowired
    private LogisticOperationsBatchLoader logisticOperationsBatchLoader;

    private static final Logger logger = LoggerFactory.getLogger(LogisticOperationsScheduler.class);

    @Scheduled(cron = "${logistic.operations.cron}")
    public void loadTable() {

        logger.info("Start Chargement flux Logistic Operations");

        try {
            logisticOperationsBatchLoader.loadLogisticOperations();
        } catch (ExceptionFonctionnelle e) {
            logger.error("Une exception s'est produite lors du chargement du flux Logistic Operations", e);
        }

        logger.info("End Chargement flux Logistic Operations");
    }
}
