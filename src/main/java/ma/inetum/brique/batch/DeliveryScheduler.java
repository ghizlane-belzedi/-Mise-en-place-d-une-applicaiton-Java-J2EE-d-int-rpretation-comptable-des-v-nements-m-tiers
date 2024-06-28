package ma.inetum.brique.batch;

import ma.inetum.brique.exception.ExceptionFonctionnelle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DeliveryScheduler {
    @Autowired
    private DeliveryBatchLoader deliveryBatchLoader;

    private static final Logger logger = LoggerFactory.getLogger(DeliveryScheduler.class);

    @Scheduled(cron = "${delivery.cron}")
    public void loadTable() {

        logger.info("Start Chargement flux Delivery Note");

        try {
            deliveryBatchLoader.loadDelivery();
        } catch (ExceptionFonctionnelle e) {
            logger.error("Une exception s'est produite lors du chargement du flux Delivery Note", e);
        }

        logger.info("End Chargement flux Delivery Note");
    }
}
