package ma.inetum.brique.model.principale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PARAM_MODE_PAIEMENT")
public class ParamModePaiement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="PARAM_MODE_PAIEMENT_SEQ")
    @SequenceGenerator(name="PARAM_MODE_PAIEMENT_SEQ", sequenceName="PARAM_MODE_PAIEMENT_SEQ", allocationSize=1)
	@Column(name = "ID")
	private Long id;
	@Column(name = "CODE")
	private String code;
	@Column(name = "DESCRIPTION")
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
}
