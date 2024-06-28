package ma.inetum.brique.utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Constantes {

	public static final String FLUX_VEH_ORD 			= "VEH_CMD_FRS";
	public static final String FLUX_VEH_FIN 			= "VEH_FAC_FRS";
	public static final String FLUX_COSTING 			= "VEH_COSTING";
	public static final String FLUX_Logistic_Operations = "VEH_STOCK";
	public static final String FLUX_INVOICE  			= "VEH_FAC_CLI";
	public static final String FLUX_DEPOSIT  			= "VEH_ACP_CLI";
	public static final String FLUX_WORKSHOP 			= "VEH_ATEL";
	public static final String FLUX_DELIVERY 			= "VEH_LIV_CLI";


	public static final DateTimeFormatter FORMAT_DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	public static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");



	public static final String VEHICULE = "VEHICULE";
	public static final String EQUIPEMENT = "EQUIPEMENT";

	public static final String OPERTYPE_S = "S";
	public static final String OPERTYPE_T = "T";

	public static final List<String> MODES_PAIEMENT_DEPOSIT = Arrays.asList("ESP", "CPT", "VER") ;
	public static final List<String> MODES_PAIEMENT_CHEQUE = Arrays.asList("CHB", "CHC", "CHQ", "REV") ;;
	public static final List<String> MODES_PAIEMENT_CARTE_N = Arrays.asList("CRT");
	public static final List<String>  MODES_PAIEMENT_CARTE_E = Arrays.asList("CRI");
	public static final List<String> MODES_PAIEMENT_EFFET = Arrays.asList("EFF");
	public static final List<String> MODES_PAIEMENT_VIREMENT = Arrays.asList("VIR", "PEL");

	public static final double TAUX_CARTE_N = 0.015;
	public static final double TAUX_CARTE_E = 0.025;

	public static final String PAIEMENT_ACCOUNT_DESCRIPTION_DEPOSIT_DEBIT = "Espèce à verser site";
	public static final String PAIEMENT_ACCOUNT_DESCRIPTION_CHEQUE_DEBIT = "Chèque à verser site";
	public static final String PAIEMENT_ACCOUNT_DESCRIPTION_CARTE_DEBIT = "Paiement carte site";
	public static final String PAIEMENT_ACCOUNT_DESCRIPTION_EFFET_DEBIT = "Effet à verser site";
	public static final String PAIEMENT_ACCOUNT_DESCRIPTION_VIREMENT_DEBIT = "Virement bancaire";
	public static final String PAIEMENT_ACCOUNT_DESCRIPTION_CREDIT= "Clients acompte";


	public static final String INVOICE_TYPE_VENTE = "I";
	public static final String INVOICE_TYPE_CREDIT_NOTE = "C";

	public static final String IMP_VHL = "IMP_VHL";
	public static final String FVN_EXO_LIV = "FVN EXO LIV";
	public static final String FVN_TTC_LIV = "FVN TTC LIV";
	public static final String FVN_EXO = "FVN EXO";
	public static final String FVN_TTC = "FVN TTC";
	public static final String AVN_EXO = "AVN EXO";
	public static final String AVN_TTC = "AVN TTC";

	public static final String INVOICE_ACCOUNT_DESCRIPTION_TVA = "Etat - TVA Facturée";
	public static final String INVOICE_ACCOUNT_VENTE_VN = "Vente VN";
	public static final String INVOICE_ACCOUNT_FRAIX_CONVOYAGE = "Frais Convoyage Vhl";
	public static final String INVOICE_ACCOUNT_MISE_EN_CIRCULATION = "Mise en Circulation Véhicule";
	public static final String INVOICE_ACCOUNT_FRAIX_IMMATRICULATION = "Frais d'immatriculation (Carte Grise)";
	public static final String INVOICE_ACCOUNT_FRAIX_WW = "Frais WW";
	public static final String INVOICE_ACCOUNT_TAXE_VOITURE_LUXE = "Taxe sur les voitures de luxe";
	public static final String INVOICE_ACCOUNT_VARIATION_STOCK = "Variation Stocks Vles Neufs";
	public static final String INVOICE_ACCOUNT_STOCK = "Variation Stocks Vles Neufs";

	public static final Double INVOICE_FRAIX_WW = 305.0;



	public static final String STOCK_VHL = "STOCK_VHL";
	public static final String STOCK_VEHICULE_NEUF = "Stock véhicules neufs";
	
	public static final String CODE_JRNL_STOCK_VHL = "OD STOCK VHL";
	public static final String INVOICE_CLIENT_ACCOMPTE = "Clients accompte";
	public static final String CODE_JRNL_IMPORT_VHL = "IMP_VHL";
	public static final String CODE_JRNL_DEPOSIT_VHL = "OD ACOMP VHL";
	
	
	public static final String COMPANY_CODE = "AUTO NEJMA";
	public static final Integer CODE_LINK_POSTED = 46; 
	
	public static final List<String> COMPTE_CODA_QUANTITE = List.of("611100", "611400", "711100", "711101", "711120", "711121", "311100");
	
	public static final DecimalFormat DECIMAL_FORMAT_MONTANT = new DecimalFormat();
	static {
		DecimalFormatSymbols symbols = new DecimalFormat().getDecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		symbols.setDecimalSeparator(',');
		DECIMAL_FORMAT_MONTANT.setDecimalFormatSymbols(symbols);
	}
	
	public static final String DEFAULT_SITE = "ZZZ";
	
	public static final char SENS_DEBIT  = 'D';
	public static final char SENS_CREDIT = 'C';
	
	public static final String VN_FACTURED_NOT_DELIVERD     = "OD DIV VHL";
	public static final String VN_FACTURED_NOT_DELIVERD_INV = "OD DIV VHL INV";
	public static final String COSTING_CREDIT_NOTE_DOC_TYPE = "C";
	public static final String COSTING_INVOICE_DOC_TYPE     = "I";
	public static final String SUPPLIER_DOUANE_CODE         = "F0028";
	public static final String DEFAULT_CUSTOMER_CODE        = "C9999";
	public static final String DEFAULT_MARQUE_CODE          = "MCOM";
	public static final String INVOICE_SALE_TYPE_3          = "3";
	public static final String INVOICE_CONCESSIONNAIRE_SITE = "SCONC";
	public static final Long DEPOSIT_SEQUENCE_2 			= 2L;
	
}
