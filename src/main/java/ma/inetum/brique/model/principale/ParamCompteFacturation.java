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
@Table(name = "PARAM_COMPTE_FACTURATION", 
	uniqueConstraints = @UniqueConstraint(columnNames = {"SITE", "TVA"}))
public class ParamCompteFacturation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="PARAM_COMPTE_FACTURATION_SEQ")
    @SequenceGenerator(name="PARAM_COMPTE_FACTURATION_SEQ", sequenceName="PARAM_COMPTE_FACTURATION_SEQ", allocationSize=1)
	@Column(name = "ID")
	private Long id;
	@Column(name = "TVA", nullable = false)
	private Boolean tva;
	@Column(name = "SITE", nullable = false)
	private String site;
	@Column(name = "COMPTE_GENERAL", nullable = false)
	private String compteGeneral;
	@Column(name = "COMPTE_CLASS3")
	private String compteClass3;
	@Column(name = "COMPTE_CLASS4")
	private String compteClass4;
	@Column(name = "DESC_1")
	private String desc1;
	@Column(name = "DESC_2")
	private String desc2;
	@Column(name = "DESC_3")
	private String desc3;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getTva() {
		return tva;
	}
	public void setTva(Boolean tva) {
		this.tva = tva;
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
	public String getCompteClass3() {
		return compteClass3;
	}
	public void setCompteClass3(String compteClass3) {
		this.compteClass3 = compteClass3;
	}
	public String getCompteClass4() {
		return compteClass4;
	}
	public void setCompteClass4(String compteClass4) {
		this.compteClass4 = compteClass4;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getDesc3() {
		return desc3;
	}
	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}
	
}
