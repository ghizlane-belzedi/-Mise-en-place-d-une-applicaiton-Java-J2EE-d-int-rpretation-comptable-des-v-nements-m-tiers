package ma.inetum.brique.model.principale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PARAM_COMPTE_VERSEMENT", uniqueConstraints = @UniqueConstraint(columnNames = {"MODE_PAIEMENT", "SITE"}))
public class ParamCompteVersement {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="PARAM_COMPTE_VERSEMENT_SEQ")
    @SequenceGenerator(name="PARAM_COMPTE_VERSEMENT_SEQ", sequenceName="PARAM_COMPTE_VERSEMENT_SEQ", allocationSize=1)
	@Column(name = "ID")
	private Long id;
	@Column(name = "MODE_PAIEMENT" , nullable = false)
	private String modePaiement;
	@Column(name = "SITE", nullable = false)
	private String site;
	@Column(name = "COMPTE_GENERAL", nullable = false)
	private String compteGeneral;
	@Column(name = "DESC")
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
