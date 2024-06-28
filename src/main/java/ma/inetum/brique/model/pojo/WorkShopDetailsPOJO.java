package ma.inetum.brique.model.pojo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class WorkShopDetailsPOJO {
//	@Column(name = "MAGIC")
//	private Long magic;
	@Column(name = "VSCOMPID")
	private String vsCompId;
//	@Column(name = "VEHICLE")
//	private Long vehicle;
//	@Column(name = "CHASSIS")
//	private String chassis;
//	@Column(name = "FRAN")
//	private String fran;
	@Column(name = "FRANDESC")
	private String franDesc;
//	@Column(name = "ANALYSIS")
//	private String analysis;
	@Column(name = "ANALDESC")
	private String analDesc;
	@Column(name = "PRODUCT")
	private String product;
	@Column(name = "PRODDESC")
	private String prodDesc;
	@Column(name = "INVALUE")
	private Double inValue;
	@Column(name = "VATVALUE")
	private Double vatValue;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "PROGRESS")
	private String progress;
	@Column(name = "COMM")
	private String comm;
	@Column(name = "LOCN")
	private String locn;
	@Column(name = "VATCODE")
	private String vatCode;
	public String getVsCompId() {
		return vsCompId;
	}
	public void setVsCompId(String vsCompId) {
		this.vsCompId = vsCompId;
	}
	public String getFranDesc() {
		return franDesc;
	}
	public void setFranDesc(String franDesc) {
		this.franDesc = franDesc;
	}
	public String getAnalDesc() {
		return analDesc;
	}
	public void setAnalDesc(String analDesc) {
		this.analDesc = analDesc;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public Double getInValue() {
		return inValue;
	}
	public void setInValue(Double inValue) {
		this.inValue = inValue;
	}
	public Double getVatValue() {
		return vatValue;
	}
	public void setVatValue(Double vatValue) {
		this.vatValue = vatValue;
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
	public String getVatCode() {
		return vatCode;
	}
	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}
	
}
