package ma.inetum.brique.bean;

public class CompteView {
	
	private Long id;
	private String flux;
	private String code ;
	private String description ;
	private String codeAnalyse;
	private String fournisseur;
	private String costCenter;
	private String tvaAccount;
	private String compteCrediteurDescription;
	private String compteCrediteur;
	private String compteDebiteurDescription;
	private String compteDebiteur;
	private String tvaAccountDescription;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFlux() {
		return flux;
	}
	public void setFlux(String flux) {
		this.flux = flux;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCodeAnalyse() {
		return codeAnalyse;
	}
	public void setCodeAnalyse(String codeAnalyse) {
		this.codeAnalyse = codeAnalyse;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public void setTvaAccount(String compteTVA) {
		this.tvaAccount = compteTVA;
		
	}
	public void setTvaDescription(String tvaAccountDescription) {
		this.tvaAccountDescription = tvaAccountDescription;
		
	}
	public void setCompteDebiteur(String compteDebiteur) {
		this.compteDebiteur =  compteDebiteur;
		
	}
	public void setCompteDebiteurDescription(String compteDebiteurDescription) {
		this.compteDebiteurDescription = compteDebiteurDescription;
		
	}
	public void setCompteCrediteur(String compteCrediteur) {
		this.compteCrediteur = compteCrediteur;
		
	}
	public void setCompteCrediteurDescription(String compteCrediteurDescription) {
		this.compteCrediteurDescription = compteCrediteurDescription; 
		
	}
	public String getTvaAccountDescription() {
		return tvaAccountDescription;
	}
	public void setTvaAccountDescription(String tvaAccountDescription) {
		this.tvaAccountDescription = tvaAccountDescription;
	}
	public String getTvaAccount() {
		return tvaAccount;
	}
	public String getCompteCrediteurDescription() {
		return compteCrediteurDescription;
	}
	public String getCompteCrediteur() {
		return compteCrediteur;
	}
	public String getCompteDebiteurDescription() {
		return compteDebiteurDescription;
	}
	public String getCompteDebiteur() {
		return compteDebiteur;
	}
	
	
}
