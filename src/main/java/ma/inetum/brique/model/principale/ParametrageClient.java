package ma.inetum.brique.model.principale;

import javax.persistence.*;

@Entity
@Table(name = "PARAM_CLIENT")
public class ParametrageClient {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "CODE_METIER")
    private String codeMedtier;
    @Column(name = "CODE_FINANCE")
    private String codeFinance;
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SITE")
    private String site;

    @Column(name = "CLIENT")
    private String client;

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

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

    
}
