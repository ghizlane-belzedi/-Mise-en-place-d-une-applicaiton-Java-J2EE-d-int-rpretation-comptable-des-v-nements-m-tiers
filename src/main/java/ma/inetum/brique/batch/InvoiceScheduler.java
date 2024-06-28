package ma.inetum.brique.batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ma.inetum.brique.exception.ExceptionFonctionnelle;

@Service
public class InvoiceScheduler {

	@Autowired
	private InvoiceBatchLoader invoiceBatchLoader;

	private static final Logger logger = LoggerFactory.getLogger(InvoiceScheduler.class);

	@Scheduled(cron = "${invoice.cron}")
	public void loadTable() {

		logger.info("Start Chargement flux INVOICE");

		try {
			invoiceBatchLoader.loadInvoice();
		} catch (ExceptionFonctionnelle e) {
			logger.error("Une exception s'est produite lors du chargement du flux VO", e);
		}

		logger.info("End Chargement flux INVOICE");
	}

}
