package ma.inetum.brique.bean;

public class SchemaEditView {
	private Long id;
	private String flux;
	private String code ;
	private String description ;
	private String codeAnalyse;
	private String fournisseur;
	private String costCenter;
	private String compteDebiteur;
	private String compteDebiteurDescription;
	private Boolean compteDebiteurActif;
	private String compteTva;
	private String compteTvaDescription;
	private Boolean compteTvaActif;
	private String compteCrediteur;
	private String compteCrediteurDescription;
	private Boolean compteCrediteurActif;
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
	public String getCompteDebiteur() {
		return compteDebiteur;
	}
	public void setCompteDebiteur(String compteDebiteur) {
		this.compteDebiteur = compteDebiteur;
	}
	public String getCompteDebiteurDescription() {
		return compteDebiteurDescription;
	}
	public void setCompteDebiteurDescription(String compteDebiteurDescription) {
		this.compteDebiteurDescription = compteDebiteurDescription;
	}
	public Boolean getCompteDebiteurActif() {
		return compteDebiteurActif;
	}
	public void setCompteDebiteurActif(Boolean compteDebiteurActif) {
		this.compteDebiteurActif = compteDebiteurActif;
	}
	public String getCompteTva() {
		return compteTva;
	}
	public void setCompteTva(String compteTva) {
		this.compteTva = compteTva;
	}
	public String getCompteTvaDescription() {
		return compteTvaDescription;
	}
	public void setCompteTvaDescription(String compteTvaDescription) {
		this.compteTvaDescription = compteTvaDescription;
	}
	public Boolean getCompteTvaActif() {
		return compteTvaActif;
	}
	public void setCompteTvaActif(Boolean compteTvaActif) {
		this.compteTvaActif = compteTvaActif;
	}
	public String getCompteCrediteur() {
		return compteCrediteur;
	}
	public void setCompteCrediteur(String compteCrediteur) {
		this.compteCrediteur = compteCrediteur;
	}
	public String getCompteCrediteurDescription() {
		return compteCrediteurDescription;
	}
	public void setCompteCrediteurDescription(String compteCrediteurDescription) {
		this.compteCrediteurDescription = compteCrediteurDescription;
	}
	public Boolean getCompteCrediteurActif() {
		return compteCrediteurActif;
	}
	public void setCompteCrediteurActif(Boolean compteCrediteurActif) {
		this.compteCrediteurActif = compteCrediteurActif;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
