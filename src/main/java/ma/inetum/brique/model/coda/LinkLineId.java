package ma.inetum.brique.model.coda;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oas_linkline")
public class LinkLineId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String linkcode;
	@Id
	private String cmpcode;
	@Id
	private String doccode;
	@Id
	private Long docnum;
	@Id
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
	public String getLinkcode() {
		return linkcode;
	}
	public void setLinkcode(String linkcode) {
		this.linkcode = linkcode;
	}

}
