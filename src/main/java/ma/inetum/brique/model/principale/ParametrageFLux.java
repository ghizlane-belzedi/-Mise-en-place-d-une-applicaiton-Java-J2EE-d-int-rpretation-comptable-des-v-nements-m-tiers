package ma.inetum.brique.model.principale;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PARAMETRAGE_FLUX")
public class ParametrageFLux {

	@Id
	private Long id;
	private String nom;
	private String code;
	@OneToMany(mappedBy="flux", cascade = CascadeType.ALL)
	private List<ParametrageFluxField> fields;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ParametrageFluxField> getFields() {
		return fields;
	}
	public void setFields(List<ParametrageFluxField> fields) {
		this.fields = fields;
	}
}
