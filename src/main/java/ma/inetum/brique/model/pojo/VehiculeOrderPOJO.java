package ma.inetum.brique.model.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class VehiculeOrderPOJO {

	@Column(name = "COMPID")
	private Long compid;
	@Column(name = "ORDER")
	private String order;
	@Column(name = "VEHICLE")
	private String vehicle;
	@Column(name = "CREDATE")
	private LocalDate credate;
	@Column(name = "COMM")
	private String comm;
	@Column(name = "SUPACC")
	private String supacc;
	@Column(name = "SUPPLIER")
	private String supplier;
	@Column(name = "ORDATE")
	private LocalDate ordate;
	@Column(name = "ORDDESC")
	private String orddesc;
	@Column(name = "ORDTYPE")
	private String ordtype;
	@Column(name = "BUYCURR")
	private String buycurr;
	@Column(name = "BUYEXCH")
	private Double buyexch;
	@Column(name = "ORDSTAT")
	private String ordstat;
	@Column(name = "RECDATEE")
	private LocalDate recdatee;
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
	@Column(name = "COLCODE")
	private String colcode;
	@Column(name = "COLDESC")
	private String coldesc;
	@Column(name = "TRIMCODE")
	private String trimcode;
	@Column(name = "TRIMDESC")
	private String trimdesc;
	@Column(name = "CONS")
	private Double cons;
	@Column(name = "FREIGHT")
	private Double freight;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "FINDATE")
	private LocalDate findate;

	public Long getCompid() {
		return compid;
	}
	public void setCompid(Long compid) {
		this.compid = compid;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public LocalDate getCredate() {
		return credate;
	}
	public void setCredate(LocalDate credate) {
		this.credate = credate;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public String getSupacc() {
		return supacc;
	}
	public void setSupacc(String supacc) {
		this.supacc = supacc;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public LocalDate getOrdate() {
		return ordate;
	}
	public void setOrdate(LocalDate ordate) {
		this.ordate = ordate;
	}
	public String getOrddesc() {
		return orddesc;
	}
	public void setOrddesc(String orddesc) {
		this.orddesc = orddesc;
	}
	public String getOrdtype() {
		return ordtype;
	}
	public void setOrdtype(String ordtype) {
		this.ordtype = ordtype;
	}
	public String getBuycurr() {
		return buycurr;
	}
	public void setBuycurr(String buycurr) {
		this.buycurr = buycurr;
	}
	public Double getBuyexch() {
		return buyexch;
	}
	public void setBuyexch(Double buyexch) {
		this.buyexch = buyexch;
	}
	public String getOrdstat() {
		return ordstat;
	}
	public void setOrdstat(String ordstat) {
		this.ordstat = ordstat;
	}
	public LocalDate getRecdatee() {
		return recdatee;
	}
	public void setRecdatee(LocalDate recdatee) {
		this.recdatee = recdatee;
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
	public String getColcode() {
		return colcode;
	}
	public void setColcode(String colcode) {
		this.colcode = colcode;
	}
	public String getColdesc() {
		return coldesc;
	}
	public void setColdesc(String coldesc) {
		this.coldesc = coldesc;
	}
	public String getTrimcode() {
		return trimcode;
	}
	public void setTrimcode(String trimcode) {
		this.trimcode = trimcode;
	}
	public String getTrimdesc() {
		return trimdesc;
	}
	public void setTrimdesc(String trimdesc) {
		this.trimdesc = trimdesc;
	}
	public Double getCons() {
		return cons;
	}
	public void setCons(Double cons) {
		this.cons = cons;
	}
	public Double getFreight() {
		return freight;
	}
	public void setFreight(Double freight) {
		this.freight = freight;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getFindate() {
		return findate;
	}
	public void setFindate(LocalDate findate) {
		this.findate = findate;
	}

}
