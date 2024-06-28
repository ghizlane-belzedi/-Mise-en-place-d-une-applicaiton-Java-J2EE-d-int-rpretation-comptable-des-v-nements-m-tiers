package ma.inetum.brique.bean;

import javax.persistence.Column;

public class ClientBean {

	private Long id;
    private String codeMedtier;
    private String codeFinance;
    private String description;
    private String site;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodeMedtier() {
		return codeMedtier;
	}
	public void setCodeMedtier(String codeMedtier) {
		this.codeMedtier = codeMedtier;
	}
	public String getCodeFinance() {
		return codeFinance;
	}
	public void setCodeFinance(String codeFinance) {
		this.codeFinance = codeFinance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
    
    
}
