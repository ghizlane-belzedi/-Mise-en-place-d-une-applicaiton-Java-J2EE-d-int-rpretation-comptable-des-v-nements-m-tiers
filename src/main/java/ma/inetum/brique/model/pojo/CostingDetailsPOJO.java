package ma.inetum.brique.model.pojo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CostingDetailsPOJO {
	@Column(name = "COMPID")
	private String compid;
	@Column(name = "FRANDESC")
	private String frandesc;
	@Column(name = "ANALDESC")
	private String analdesc;
	@Column(name = "PRODUCT")
	private String product;
	@Column(name = "PRODDESC")
	private String proddesc;
	@Column(name = "INVALUE")
	private Double invalue;
	@Column(name = "VATCODE")
	private String vatcode;
	@Column(name = "VATVALUE")
	private Double vatvalue;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "PROGRESS")
	private String progress;
	@Column(name = "COMM")
	private String comm;
	@Column(name = "LOCN")
	private String locn;
	public String getCompid() {
		return compid;
	}
	public void setCompid(String compid) {
		this.compid = compid;
	}
	public String getFrandesc() {
		return frandesc;
	}
	public void setFrandesc(String frandesc) {
		this.frandesc = frandesc;
	}
	public String getAnaldesc() {
		return analdesc;
	}
	public void setAnaldesc(String analdesc) {
		this.analdesc = analdesc;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProddesc() {
		return proddesc;
	}
	public void setProddesc(String proddesc) {
		this.proddesc = proddesc;
	}
	public Double getInvalue() {
		return invalue;
	}
	public void setInvalue(Double invalue) {
		this.invalue = invalue;
	}
	public Double getVatvalue() {
		return vatvalue;
	}
	public void setVatvalue(Double vatvalue) {
		this.vatvalue = vatvalue;
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
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public String getLocn() {
		return locn;
	}
	public void setLocn(String locn) {
		this.locn = locn;
	}
	public String getVatcode() {
		return vatcode;
	}
	public void setVatcode(String vatcode) {
		this.vatcode = vatcode;
	}
	
	
}