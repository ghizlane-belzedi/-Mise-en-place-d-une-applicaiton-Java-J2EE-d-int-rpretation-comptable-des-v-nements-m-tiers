package ma.inetum.brique.model.principale;

import javax.persistence.*;

@Entity
@Table(name = "PARAM_MAPPING")
public class Parametrage {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "DOMAIN_ID")
//	private Domaine domaine;
	@Column(name = "CATEGORIE")
	private String categorie;
	@Column(name = "CODE_METIER")
	private String codeMedtier;
	@Column(name = "CODE_FINANCE")
	private String codeFinance;
	@Column(name = "DESCRIPTION")
	private String description1;
	@Column(name="ADDITIONAL_FIELD")
	private String addtionalField;

	@Column(name="ADDITIONAL_FIELD_DESCR")
	private String addtionalFieldDescr;
	public String getAddtionalField() {
		return addtionalField;
	}

	public void setAddtionalField(String addtionalField) {
		this.addtionalField = addtionalField;
	}

	public String getAddtionalFieldDescr() {
		return addtionalFieldDescr;
	}

	public void setAddtionalFieldDescr(String addtionalFieldDescr) {
		this.addtionalFieldDescr = addtionalFieldDescr;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
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
	public String getDescription1() {
		return description1;
	}
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
}
