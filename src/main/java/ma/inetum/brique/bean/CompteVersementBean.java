package ma.inetum.brique.bean;

public class CompteVersementBean {

	private Long id;
	private String modePaiement;
	private String site;
	private String compteGeneral;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModePaiement() {
		return modePaiement;
	}
	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getCompteGeneral() {
		return compteGeneral;
	}
	public void setCompteGeneral(String compteGeneral) {
		this.compteGeneral = compteGeneral;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
