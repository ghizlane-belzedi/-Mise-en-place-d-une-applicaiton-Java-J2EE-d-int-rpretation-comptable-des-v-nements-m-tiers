package ma.inetum.brique.model.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CostingHeaderPOJO {
	@Column(name = "COMPID")
	private String compid;
	@Column(name = "SHIPNO")
	private String shipno;
	@Column(name = "SHIPDATE")
	private LocalDate shipdate;
	@Column(name = "SHIPNAME")
	private String shipname;
	@Column(name = "SUPACC")
	private String supacc;
	@Column(name = "SUPPLIER")
	private String supplier;
	@Column(name = "INVNO")
	private Long invno;
	@Column(name = "INVDATE")
	private LocalDate invdate;
	@Column(name = "INVALUE")
	private Double invalue;
	@Column(name = "VATCODE")
	private String vatcode;
	@Column(name = "VATVALUE")
	private Double vatvalue;
	@Column(name = "IMFILENO")
	private String imfileno;
	@Column(name = "IMCOMMNO")
	private String imcommno;
	@Column(name = "CURRCODE")
	private String currcode;
	@Column(name = "EXCHRATE")
	private Double exchrate;
	@Column(name = "CREDATE")
	private LocalDate creationDate;
	@Column(name = "USERID")
	private String userId;
	@Column(name="SUPPREF")
	private String invoiceSupplierReference;
	@Column(name="MISCREF")
	private String invoiceSupplierReferenceCompl;
	@Column(name="DOCTYPE")
	private String docType;
	
	public String getCompid() {
		return compid;
	}
	public void setCompid(String compid) {
		this.compid = compid;
	}
	public String getShipno() {
		return shipno;
	}
	public void setShipno(String shipno) {
		this.shipno = shipno;
	}
	public LocalDate getShipdate() {
		return shipdate;
	}
	public void setShipdate(LocalDate shipdate) {
		this.shipdate = shipdate;
	}
	public String getShipname() {
		return shipname;
	}
	public void setShipname(String shipname) {
		this.shipname = shipname;
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
	public Long getInvno() {
		return invno;
	}
	public void setInvno(Long invno) {
		this.invno = invno;
	}
	public LocalDate getInvdate() {
		return invdate;
	}
	public void setInvdate(LocalDate invdate) {
		this.invdate = invdate;
	}
	public Double getInvalue() {
		return invalue;
	}
	public void setInvalue(Double invalue) {
		this.invalue = invalue;
	}
	public String getVatcode() {
		return vatcode;
	}
	public void setVatcode(String vatcode) {
		this.vatcode = vatcode;
	}
	public Double getVatvalue() {
		return vatvalue;
	}
	public void setVatvalue(Double vatvalue) {
		this.vatvalue = vatvalue;
	}
	public String getImfileno() {
		return imfileno;
	}
	public void setImfileno(String imfileno) {
		this.imfileno = imfileno;
	}
	public String getImcommno() {
		return imcommno;
	}
	public void setImcommno(String imcommno) {
		this.imcommno = imcommno;
	}
	public String getCurrcode() {
		return currcode;
	}
	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}
	public Double getExchrate() {
		return exchrate;
	}
	public void setExchrate(Double exchrate) {
		this.exchrate = exchrate;
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
	public String getInvoiceSupplierReference() {
		return invoiceSupplierReference;
	}
	public void setInvoiceSupplierReference(String invoiceSupplierReference) {
		this.invoiceSupplierReference = invoiceSupplierReference;
	}
	public String getInvoiceSupplierReferenceCompl() {
		return invoiceSupplierReferenceCompl;
	}
	public void setInvoiceSupplierReferenceCompl(String invoiceSupplierReferenceCompl) {
		this.invoiceSupplierReferenceCompl = invoiceSupplierReferenceCompl;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
}
