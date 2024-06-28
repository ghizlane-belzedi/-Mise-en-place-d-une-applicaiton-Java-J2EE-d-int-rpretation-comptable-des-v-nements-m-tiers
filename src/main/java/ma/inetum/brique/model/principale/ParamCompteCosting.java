package ma.inetum.brique.model.principale;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARAM_COMPTE_COSTING")
public class ParamCompteCosting {

	@Id
	@GeneratedValue
	@Column(name = "ID_SCHEMA")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLUX_ID")
	private ParametrageFLux flux;
	@Column(name = "COST_CENTER")
	private String costCenter;
	@Column(name = "CODE_ANALYSE")
	private String codeAnalyse;
	@Column(name = "CODE_FOURNISSEUR")
	private String codeFournisseur;
	@Column(name = "CODE")
	private String code ;
	@Column(name = "DESCRIPTION")
	private String description ;
	@Column(name = "COMPTE_TVA")
	private String compteTVA;
	@Column(name = "TVA_ACCOUNT_DESCRIPTION")
	private String tvaAccountDescription;
	@Column(name = "COMPTE_CREDITEUR")
	private String compteCrediteur;
	@Column(name = "ACCOUNT_CREDITEUR_DESCRIPTION")
	private String compteCrediteurDescription;
	@Column(name = "COMPTE_DEBITEUR")
	private String compteDebiteur;
	@Column(name = "ACCOUNT_DEBITEUR_DESCRIPTION")
	private String compteDebiteurDescription;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ParametrageFLux getFlux() {
		return flux;
	}
	public void setFlux(ParametrageFLux flux) {
		this.flux = flux;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getCodeAnalyse() {
		return codeAnalyse;
	}
	public void setCodeAnalyse(String codeAnalyse) {
		this.codeAnalyse = codeAnalyse;
	}
	public String getCodeFournisseur() {
		return codeFournisseur;
	}
	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
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
	public String getCompteTVA() {
		return compteTVA;
	}
	public void setCompteTVA(String compteTVA) {
		this.compteTVA = compteTVA;
	}
	public String getCompteCrediteur() {
		return compteCrediteur;
	}
	public void setCompteCrediteur(String compteCrediteur) {
		this.compteCrediteur = compteCrediteur;
	}
	public String getCompteDebiteur() {
		return compteDebiteur;
	}
	public void setCompteDebiteur(String compteDebiteur) {
		this.compteDebiteur = compteDebiteur;
	}
	public String getTvaAccountDescription() {
		return tvaAccountDescription;
	}
	public void setTvaAccountDescription(String tvaAccountDescription) {
		this.tvaAccountDescription = tvaAccountDescription;
	}
	public String getCompteCrediteurDescription() {
		return compteCrediteurDescription;
	}
	public void setCompteCrediteurDescription(String compteCrediteurDescription) {
		this.compteCrediteurDescription = compteCrediteurDescription;
	}
	public String getCompteDebiteurDescription() {
		return compteDebiteurDescription;
	}
	public void setCompteDebiteurDescription(String compteDebiteurDescription) {
		this.compteDebiteurDescription = compteDebiteurDescription;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codeAnalyse, codeFournisseur, compteCrediteur, compteDebiteur, compteTVA, costCenter, flux);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParamCompteCosting other = (ParamCompteCosting) obj;
		return Objects.equals(codeAnalyse, other.codeAnalyse) && Objects.equals(codeFournisseur, other.codeFournisseur)
				&& Objects.equals(compteCrediteur, other.compteCrediteur)
				&& Objects.equals(compteDebiteur, other.compteDebiteur) && Objects.equals(compteTVA, other.compteTVA)
				&& Objects.equals(costCenter, other.costCenter) && Objects.equals(flux, other.flux);
	}
}
