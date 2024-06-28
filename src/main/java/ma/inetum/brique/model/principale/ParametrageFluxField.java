package ma.inetum.brique.model.principale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARAMETRAGE_FLUX_FIELD")
public class ParametrageFluxField {
	@Id
	private Long id;
	private String description;
	private String codeBDD;
	@Column(name = "code_entite")
	private String codeEntite;
	private Integer ordre;
	private Boolean visible;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLUX_ID")
	private ParametrageFLux flux;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCodeBDD() {
		return codeBDD;
	}


	public void setCodeBDD(String codeBDD) {
		this.codeBDD = codeBDD;
	}


	public String getCodeEntite() {
		return codeEntite;
	}


	public void setCodeEntite(String codeEntite) {
		this.codeEntite = codeEntite;
	}


	public Integer getOrdre() {
		return ordre;
	}


	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}


	public Boolean getVisible() {
		return visible;
	}


	public void setVisible(Boolean visible) {
		this.visible = visible;
	}


	public ParametrageFLux getFlux() {
		return flux;
	}


	public void setFlux(ParametrageFLux flux) {
		this.flux = flux;
	}
}
