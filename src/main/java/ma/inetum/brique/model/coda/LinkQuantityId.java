package ma.inetum.brique.model.coda;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oas_linkqty")
public class LinkQuantityId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "cmpcode")
	private String cmpcode;
	@Id
	@Column(name = "doccode")
	private String doccode;
	@Id
	@Column(name = "docnum")
	private Long docnum;
	@Id
	@Column(name = "doclinenum")
	private Integer doclinenum;
	public String getCmpcode() {
		return cmpcode;
	}
	public void setCmpcode(String cmpcode) {
		this.cmpcode = cmpcode;
	}
	public String getDoccode() {
		return doccode;
	}
	public void setDoccode(String doccode) {
		this.doccode = doccode;
	}
	public Long getDocnum() {
		return docnum;
	}
	public void setDocnum(Long docnum) {
		this.docnum = docnum;
	}
	public Integer getDoclinenum() {
		return doclinenum;
	}
	public void setDoclinenum(Integer doclinenum) {
		this.doclinenum = doclinenum;
	}
	
	
}
