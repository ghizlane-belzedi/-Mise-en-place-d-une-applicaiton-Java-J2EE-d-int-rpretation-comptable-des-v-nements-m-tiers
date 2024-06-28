package ma.inetum.brique.model.coda;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "OAS_LINKQTY")
@IdClass(LinkQuantityId.class)
public class LinkQuantity {
	
	private String cmpcode;
	private String doccode;
	private Long docnum;
	private Integer doclinenum;
	private Integer elmlevel;
	private Integer qtyposition;
	private Double qtyvalue;
	private Integer qtyvalue_dp;
	
	@Id
	@Column(name = "\"CMPCODE\"")
	public String getCmpcode() {
		return cmpcode;
	}
	public void setCmpcode(String cmpcode) {
		this.cmpcode = cmpcode;
	}
	@Id
	@Column(name = "\"DOCCODE\"")
	public String getDoccode() {
		return doccode;
	}
	public void setDoccode(String doccode) {
		this.doccode = doccode;
	}
	@Id
	@Column(name = "\"DOCNUM\"")
	public Long getDocnum() {
		return docnum;
	}
	public void setDocnum(Long docnum) {
		this.docnum = docnum;
	}
	@Id
	@Column(name = "\"DOCLINENUM\"")
	public Integer getDoclinenum() {
		return doclinenum;
	}
	public void setDoclinenum(Integer doclinenum) {
		this.doclinenum = doclinenum;
	}
	@Column(name = "\"ELMLEVEL\"")
	public Integer getElmlevel() {
		return elmlevel;
	}
	public void setElmlevel(Integer elmlevel) {
		this.elmlevel = elmlevel;
	}
	@Column(name = "\"QTYPOSITION\"")
	public Integer getQtyposition() {
		return qtyposition;
	}
	public void setQtyposition(Integer qtyposition) {
		this.qtyposition = qtyposition;
	}
	@Column(name = "\"QTYVALUE\"")
	public Double getQtyvalue() {
		return qtyvalue;
	}
	public void setQtyvalue(Double qtyvalue) {
		this.qtyvalue = qtyvalue;
	}
	@Column(name = "\"QTYVALUE_DP\"")
	public Integer getQtyvalue_dp() {
		return qtyvalue_dp;
	}
	public void setQtyvalue_dp(Integer qtyvalue_dp) {
		this.qtyvalue_dp = qtyvalue_dp;
	}
	
	
	
	
}
