package ma.inetum.brique.model.coda;

import java.io.Serializable;

import javax.persistence.Id;

public class LinkHeadId  implements Serializable{
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
	public String getLinkcode() {
		return linkcode;
	}
	public void setLinkcode(String linkcode) {
		this.linkcode = linkcode;
	}
		
	
}