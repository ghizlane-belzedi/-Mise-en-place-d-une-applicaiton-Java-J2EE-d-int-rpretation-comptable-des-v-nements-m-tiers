package ma.inetum.brique.model.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DepositPOJO {
	@Column(name = "MAGIC")
	private Long magic;	
	@Column(name = "DOCNUM")
	private Long docnum;
	@Column(name = "DOCDATE")
	private LocalDate docdate;
	@Column(name = "LNKCOMP")
	private String lnkcomp;
	@Column(name = "ORDNUM")
	private Long ordnum;
	@Column(name = "ORDDATE")
	private LocalDate orddate;
	@Column(name = "CUSTCODE")
	private Long custcode;
	@Column(name = "CUSTNAME")
	private String custname;
	@Column(name = "ACCOUNT")
	private String account;
	@Column(name = "ACCNAME")
	private String accname;
	@Column(name = "VSCOMPID")
	private String vscompid;
	@Column(name = "VEHICLE")
	private Long vehicle;
	@Column(name = "CHASSIS")
	private String chassis;
	@Column(name = "FRAN")
	private String fran;
	@Column(name = "FRANDESC")
	private String frandesc;
	@Column(name = "MODEL")
	private String model;
	@Column(name = "MODDESC")
	private String moddesc;
	@Column(name = "VARIANT")
	private String variant;
	@Column(name = "VARDESC")
	private String vardesc;
	@Column(name = "PAYMCODE")
	private String paymcode;
	@Column(name = "DEPREF")
	private String depref;
	@Column(name = "DEPVALUE")
	private Double depvalue;
	@Column(name = "VATVALUE")
	private Double vatvalue;
	@Column(name = "VATCODE")
	private String vatcode;
	@Column(name = "STAMPDUT")
	private Double stampdut;
	@Column(name = "SELLPRIC")
	private Double sellprice;
	@Column(name = "CREDATE")
	private LocalDate creationDate;
	@Column(name = "USERID")
	private String userId;
	@Column(name = "SEQUENCE")
	private Long Sequence;
	@Column(name = "LOCATION")
	private String location;
	
	public Long getDocnum() {
		return docnum;
	}
	public void setDocnum(Long docnum) {
		this.docnum = docnum;
	}
	public LocalDate getDocdate() {
		return docdate;
	}
	public void setDocdate(LocalDate docdate) {
		this.docdate = docdate;
	}
	public String getLnkcomp() {
		return lnkcomp;
	}
	public void setLnkcomp(String lnkcomp) {
		this.lnkcomp = lnkcomp;
	}
	public Long getOrdnum() {
		return ordnum;
	}
	public void setOrdnum(Long ordnum) {
		this.ordnum = ordnum;
	}
	public LocalDate getOrddate() {
		return orddate;
	}
	public void setOrddate(LocalDate orddate) {
		this.orddate = orddate;
	}
	public Long getCustcode() {
		return custcode;
	}
	public void setCustcode(Long custcode) {
		this.custcode = custcode;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
	}
	public String getVscompid() {
		return vscompid;
	}
	public void setVscompid(String vscompid) {
		this.vscompid = vscompid;
	}
	public Long getVehicle() {
		return vehicle;
	}
	public void setVehicle(Long vehicle) {
		this.vehicle = vehicle;
	}
	public String getChassis() {
		return chassis;
	}
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	public String getFran() {
		return fran;
	}
	public void setFran(String fran) {
		this.fran = fran;
	}
	public String getFrandesc() {
		return frandesc;
	}
	public void setFrandesc(String frandesc) {
		this.frandesc = frandesc;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModdesc() {
		return moddesc;
	}
	public void setModdesc(String moddesc) {
		this.moddesc = moddesc;
	}
	public String getVariant() {
		return variant;
	}
	public void setVariant(String variant) {
		this.variant = variant;
	}
	public String getVardesc() {
		return vardesc;
	}
	public void setVardesc(String vardesc) {
		this.vardesc = vardesc;
	}
	public String getPaymcode() {
		return paymcode;
	}
	public void setPaymcode(String paymcode) {
		this.paymcode = paymcode;
	}
	public String getDepref() {
		return depref;
	}
	public void setDepref(String depref) {
		this.depref = depref;
	}
	public Double getDepvalue() {
		return depvalue;
	}
	public void setDepvalue(Double depvalue) {
		this.depvalue = depvalue;
	}
	public Double getVatvalue() {
		return vatvalue;
	}
	public void setVatvalue(Double vatvalue) {
		this.vatvalue = vatvalue;
	}
	public String getVatcode() {
		return vatcode;
	}
	public void setVatcode(String vatcode) {
		this.vatcode = vatcode;
	}
	public Double getStampdut() {
		return stampdut;
	}
	public void setStampdut(Double stampdut) {
		this.stampdut = stampdut;
	}
	public Double getSellprice() {
		return sellprice;
	}
	public void setSellprice(Double sellprice) {
		this.sellprice = sellprice;
	}
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getSequence() {
		return Sequence;
	}
	public void setSequence(Long sequence) {
		Sequence = sequence;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getMagic() {
		return magic;
	}
	public void setMagic(Long magic) {
		this.magic = magic;
	}

}
