package ma.inetum.brique.model.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class WorkShopHeaderPOJO {
	
	@Column(name = "VSCOMPID")
	private String vsCompId;
	@Column(name = "ACCOUNT")
	private String account;
	@Column(name = "CUSTNAME")
	private String custName;
	@Column(name = "INVNO")
	private Long invNo;
	@Column(name = "INVDATE")
	private LocalDate invDate;
	@Column(name = "INVALUE")
	private Double inValue;
	@Column(name = "VATCODE")
	private String vatCode;
	@Column(name = "VATVALUE")
	private Double vatValue;
	@Column(name = "WIPNO")
	private Long wipNo;
	@Column(name = "BRANCHNO")
	private String branchNo;
	@Column(name = "CREDATE")
	private LocalDate creationDate;
	@Column(name = "USERID")
	private String userId;
	public String getVsCompId() {
		return vsCompId;
	}
	public void setVsCompId(String vsCompId) {
		this.vsCompId = vsCompId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Long getInvNo() {
		return invNo;
	}
	public void setInvNo(Long invNo) {
		this.invNo = invNo;
	}
	public LocalDate getInvDate() {
		return invDate;
	}
	public void setInvDate(LocalDate invDate) {
		this.invDate = invDate;
	}
	public Double getInValue() {
		return inValue;
	}
	public void setInValue(Double inValue) {
		this.inValue = inValue;
	}
	public String getVatCode() {
		return vatCode;
	}
	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}
	public Double getVatValue() {
		return vatValue;
	}
	public void setVatValue(Double vatValue) {
		this.vatValue = vatValue;
	}
	public Long getWipNo() {
		return wipNo;
	}
	public void setWipNo(Long wipNo) {
		this.wipNo = wipNo;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
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
	
}
