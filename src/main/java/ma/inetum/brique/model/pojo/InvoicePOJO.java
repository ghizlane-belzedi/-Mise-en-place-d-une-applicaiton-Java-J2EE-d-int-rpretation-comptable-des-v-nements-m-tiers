package ma.inetum.brique.model.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class InvoicePOJO {
	@Column(name = "COMPID")
	private String compid;
	@Column(name = "OPERTYPE")
	private String opertype;
	@Column(name = "SELLLOCN")
	private String selllocn;
	@Column(name = "INVOICE")
	private Long invoice;
	@Column(name = "INVDATE")
	private LocalDate invdate;
	@Column(name = "CRNNO")
	private Long crnno;
	@Column(name = "CRNDATE")
	private LocalDate crndate;
	@Column(name = "CUSTORD")
	private String custord;
	@Column(name = "CUSDATE")
	private LocalDate cusdate;
	@Column(name = "CUSTCODE")
	private String custcode;
	@Column(name = "CUSTNAME")
	private String custname;
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
	@Column(name = "REGN")
	private String regn;
	@Column(name = "SALENET")
	private Double salenet;
	@Column(name = "RECPRI1")
	private Double recpri1;
	@Column(name = "RECPRI2")
	private Double recpri2;
	@Column(name = "RECPRI3")
	private Double recpri3;
	@Column(name = "RECPRI4")
	private Double recpri4;
	@Column(name = "RECPRI5")
	private Double recpri5;
	@Column(name = "RECPRI6")
	private Double recpri6;
	@Column(name = "RECPRI7")
	private Double recpri7;
	@Column(name = "RECPRI8")
	private Double recpri8;
	@Column(name = "RECPRI9")
	private Double recpri9;
	@Column(name = "RECPRI10")
	private Double recpri10;
	@Column(name = "RECPRI11")
	private Double recpri11;
	@Column(name = "RECPRI12")
	private Double recpri12;
	@Column(name = "RECPRI13")
	private Double recpri13;
	@Column(name = "RECPRI14")
	private Double recpri14;
	@Column(name = "RECPRI15")
	private Double recpri15;
	@Column(name = "SALEVAT")
	private Double salevat;
	@Column(name = "LUXTAX")
	private Double vehtax;
	@Column(name = "INVALUE")
	private Double invalue;
	@Column(name = "STYPE")
	private String stype;
	@Column(name = "CREDAGNO")
	private String credagno;
	@Column(name = "VATEXNO")
	private String vatexno;
	@Column(name = "VATEXDAT")
	private LocalDate vatexdat;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "PROGRESS")
	private String progress;
	@Column(name = "DELDATE")
	private LocalDate deldate;
	@Column(name = "DELNOTE")
	private String delnote;
	@Column(name = "CREDATE")
	private LocalDate credate;
	@Column(name = "USERID")
	private String userid;
	@Column(name = "TOTCOST")
	private Double totcost;
	@Column(name = "TOTNRCST")
	private Double totnrcst;
	@Column(name = "VEHCOST")
	private Double vehcost;
	@Column(name = "INVACC")
	private String invacc;
	@Column(name = "INVNAME")
	private String invname;
	@Column(name = "CUSACC")
	private String cusacc;
	@Column(name = "CUSNAME")
	private String cusname;
	public String getCompid() {
		return compid;
	}
	public void setCompid(String compid) {
		this.compid = compid;
	}
	public String getOpertype() {
		return opertype;
	}
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}
	public String getSelllocn() {
		return selllocn;
	}
	public void setSelllocn(String selllocn) {
		this.selllocn = selllocn;
	}
	public Long getInvoice() {
		return invoice;
	}
	public void setInvoice(Long invoice) {
		this.invoice = invoice;
	}
	public LocalDate getInvdate() {
		return invdate;
	}
	public void setInvdate(LocalDate invdate) {
		this.invdate = invdate;
	}
	public Long getCrnno() {
		return crnno;
	}
	public void setCrnno(Long crnno) {
		this.crnno = crnno;
	}
	public LocalDate getCrndate() {
		return crndate;
	}
	public void setCrndate(LocalDate crndate) {
		this.crndate = crndate;
	}
	public String getCustord() {
		return custord;
	}
	public void setCustord(String custord) {
		this.custord = custord;
	}
	public LocalDate getCusdate() {
		return cusdate;
	}
	public void setCusdate(LocalDate cusdate) {
		this.cusdate = cusdate;
	}
	public String getCustcode() {
		return custcode;
	}
	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
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
	public String getRegn() {
		return regn;
	}
	public void setRegn(String regn) {
		this.regn = regn;
	}
	public Double getSalenet() {
		return salenet;
	}
	public void setSalenet(Double salenet) {
		this.salenet = salenet;
	}
	public Double getRecpri1() {
		return recpri1;
	}
	public void setRecpri1(Double recpri1) {
		this.recpri1 = recpri1;
	}
	public Double getRecpri2() {
		return recpri2;
	}
	public void setRecpri2(Double recpri2) {
		this.recpri2 = recpri2;
	}
	public Double getRecpri3() {
		return recpri3;
	}
	public void setRecpri3(Double recpri3) {
		this.recpri3 = recpri3;
	}
	public Double getRecpri4() {
		return recpri4;
	}
	public void setRecpri4(Double recpri4) {
		this.recpri4 = recpri4;
	}
	public Double getRecpri5() {
		return recpri5;
	}
	public void setRecpri5(Double recpri5) {
		this.recpri5 = recpri5;
	}
	public Double getRecpri6() {
		return recpri6;
	}
	public void setRecpri6(Double recpri6) {
		this.recpri6 = recpri6;
	}
	public Double getRecpri7() {
		return recpri7;
	}
	public void setRecpri7(Double recpri7) {
		this.recpri7 = recpri7;
	}
	public Double getRecpri8() {
		return recpri8;
	}
	public void setRecpri8(Double recpri8) {
		this.recpri8 = recpri8;
	}
	public Double getRecpri9() {
		return recpri9;
	}
	public void setRecpri9(Double recpri9) {
		this.recpri9 = recpri9;
	}
	public Double getRecpri10() {
		return recpri10;
	}
	public void setRecpri10(Double recpri10) {
		this.recpri10 = recpri10;
	}
	public Double getRecpri11() {
		return recpri11;
	}
	public void setRecpri11(Double recpri11) {
		this.recpri11 = recpri11;
	}
	public Double getRecpri12() {
		return recpri12;
	}
	public void setRecpri12(Double recpri12) {
		this.recpri12 = recpri12;
	}
	public Double getRecpri13() {
		return recpri13;
	}
	public void setRecpri13(Double recpri13) {
		this.recpri13 = recpri13;
	}
	public Double getRecpri14() {
		return recpri14;
	}
	public void setRecpri14(Double recpri14) {
		this.recpri14 = recpri14;
	}
	public Double getRecpri15() {
		return recpri15;
	}
	public void setRecpri15(Double recpri15) {
		this.recpri15 = recpri15;
	}
	public Double getSalevat() {
		return salevat;
	}
	public void setSalevat(Double salevat) {
		this.salevat = salevat;
	}
	public Double getVehtax() {
		return vehtax;
	}
	public void setVehtax(Double vehtax) {
		this.vehtax = vehtax;
	}
	public Double getInvalue() {
		return invalue;
	}
	public void setInvalue(Double invalue) {
		this.invalue = invalue;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getCredagno() {
		return credagno;
	}
	public void setCredagno(String credagno) {
		this.credagno = credagno;
	}
	public String getVatexno() {
		return vatexno;
	}
	public void setVatexno(String vatexno) {
		this.vatexno = vatexno;
	}
	public LocalDate getVatexdat() {
		return vatexdat;
	}
	public void setVatexdat(LocalDate vatexdat) {
		this.vatexdat = vatexdat;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public LocalDate getDeldate() {
		return deldate;
	}
	public void setDeldate(LocalDate deldate) {
		this.deldate = deldate;
	}
	public String getDelnote() {
		return delnote;
	}
	public void setDelnote(String delnote) {
		this.delnote = delnote;
	}
	public LocalDate getCredate() {
		return credate;
	}
	public void setCredate(LocalDate credate) {
		this.credate = credate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Double getTotcost() {
		return totcost;
	}
	public void setTotcost(Double totcost) {
		this.totcost = totcost;
	}
//	public Double getTotnrcost() {
//		return totnrcost;
//	}
//	public void setTotnrcost(Double totnrcost) {
//		this.totnrcost = totnrcost;
//	}
	public Double getVehcost() {
		return vehcost;
	}
	public void setVehcost(Double vehcost) {
		this.vehcost = vehcost;
	}
	public String getInvacc() {
		return invacc;
	}
	public void setInvacc(String invacc) {
		this.invacc = invacc;
	}
	public String getInvname() {
		return invname;
	}
	public void setInvname(String invname) {
		this.invname = invname;
	}
	public String getCusacc() {
		return cusacc;
	}
	public void setCusacc(String cusacc) {
		this.cusacc = cusacc;
	}
	public String getCusname() {
		return cusname;
	}
	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
	public Double getTotnrcst() {
		return totnrcst;
	}
	public void setTotnrcst(Double totnrcst) {
		this.totnrcst = totnrcst;
	}

}
