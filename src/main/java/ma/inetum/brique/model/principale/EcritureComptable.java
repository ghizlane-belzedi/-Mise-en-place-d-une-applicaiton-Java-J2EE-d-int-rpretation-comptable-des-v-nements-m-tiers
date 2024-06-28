package ma.inetum.brique.model.principale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "ECRITURE_COMPTABLE")
public class EcritureComptable implements Cloneable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="ECRITURE_COMPTABLE_SEQ")
    @SequenceGenerator(name="ECRITURE_COMPTABLE_SEQ", sequenceName="ECRITURE_COMPTABLE_SEQ", allocationSize=1)
	@Column(name = "ID_ECRITURE")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "PIECE_ID")
	private PieceComptable piece;
	@Column(name = "NCPT")
	private String ncpt;
	@Column(name = "COST_CENTER")
	private String costCenter;
	@Column(name = "ACCOUNT_DESCRIPTION")
	private String accountDescription;
	@Column(name = "MONTANT")
	private Double montant;
	@Column(name = "MONTANT_MAD")
	private Double montantMAD;
	@Column(name = "SENS")
	private Character sens;
	
	@Column(name = "REF_1")
	private String ref1;
	@Column(name = "REF_2")
	private String ref2;
	@Column(name = "REF_3")
	private String ref3;
	@Column(name = "REF_4")
	private String ref4;
	@Column(name = "REF_5")
	private String ref5;
	@Column(name = "REF_6")
	private String ref6;
	@Column(name = "REF_7")
	private String ref7;
	@Column(name = "REF_8")
	private String ref8;
	
	@Column(name = "CURRENCY_CODE")
	private String currencyCode;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PieceComptable getPiece() {
		return piece;
	}
	public void setPiece(PieceComptable piece) {
		this.piece = piece;
	}
	public String getNcpt() {
		return ncpt;
	}
	public void setNcpt(String ncpt) {
		this.ncpt = ncpt;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getAccountDescription() {
		return accountDescription;
	}
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public Double getMontantMAD() {
		return montantMAD;
	}
	public void setMontantMAD(Double montantMAD) {
		this.montantMAD = montantMAD;
	}
	public Character getSens() {
		return sens;
	}
	public void setSens(Character sens) {
		this.sens = sens;
	}
	public String getRef1() {
		return ref1;
	}
	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}
	public String getRef2() {
		return ref2;
	}
	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	public String getRef3() {
		return ref3;
	}
	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}
	public String getRef4() {
		return ref4;
	}
	public void setRef4(String ref4) {
		this.ref4 = ref4;
	}
	public String getRef5() {
		return ref5;
	}
	public void setRef5(String ref5) {
		this.ref5 = ref5;
	}
	public String getRef6() {
		return ref6;
	}
	public void setRef6(String ref6) {
		this.ref6 = ref6;
	}
	public EcritureComptable() {
	}
	public EcritureComptable(String ref1, String ref2, String ref3, String ref4, String ref5, String ref6) {
		super();
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.ref3 = ref3;
		this.ref4 = ref4;
		this.ref5 = ref5;
		this.ref6 = ref6!=null?ref6:"";
	}  
	
	public String getRef7() {
		return ref7;
	}
	public void setRef7(String ref7) {
		this.ref7 = ref7;
	}
	public String getRef8() {
		return ref8;
	}
	public void setRef8(String ref8) {
		this.ref8 = ref8;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
//		EcritureComptable ecriture = (EcritureComptable) super.clone();
		EcritureComptable ecriture = new EcritureComptable();
		ecriture.ref1 = this.ref1;
		ecriture.ref2 = this.ref2;
		ecriture.ref3 = this.ref3;
		ecriture.ref4 = this.ref4;
		ecriture.ref5 = this.ref5;
		ecriture.ref6 = this.ref6;
		ecriture.piece = this.piece;
		return ecriture;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
