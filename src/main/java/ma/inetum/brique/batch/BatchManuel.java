package ma.inetum.brique.batch;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.utilities.Constantes;

public class BatchManuel implements Runnable {
	private volatile String flux;
	public BatchManuel(String flux) {
		super();
		this.flux = flux;
	}
	
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
	@Transactional
	public void run() {
		try {
			if (Constantes.FLUX_VEH_ORD.equalsIgnoreCase(flux)) {
				vehicleOrderBatchLoader.loadVehicleOrder();
			} else if (Constantes.FLUX_VEH_FIN.equalsIgnoreCase(flux)) {
				vehicleFinalisationBatchLoader.loadVehicleFinalisation();
			} else if (Constantes.FLUX_COSTING.equalsIgnoreCase(flux)) {
				costingBatchLoader.loadCosting();
			} else if (Constantes.FLUX_WORKSHOP.equalsIgnoreCase(flux)) {
				workShopBatchLoader.loadCosting();
			} else if (Constantes.FLUX_Logistic_Operations.equalsIgnoreCase(flux)) {
				logisticBatchLoader.loadLogisticOperations();
			} else if (Constantes.FLUX_DEPOSIT.equalsIgnoreCase(flux)) {
				depositBatchLoader.loadDeposits();
			} else if (Constantes.FLUX_INVOICE.equalsIgnoreCase(flux)) {
				invoiceBatchLoader.loadInvoice();
			} else if (Constantes.FLUX_DELIVERY.equalsIgnoreCase(flux)) {
				deliveryBatchLoader.loadDelivery();
			}
		} catch (Exception e) {

		} catch (ExceptionFonctionnelle e) {
			
			e.printStackTrace();
		}
	}
}
