package ma.inetum.brique.model.coda;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "OAS_LINKHEAD")
@IdClass(LinkHeadId.class)
public class LinkHead {
	private String linkcode;
	private String cmpcode;
	private String doccode;
	private Long docnum;
	private Integer posted;
	private Integer yr;
	
	
	private Integer period;
	
	
	private String curdoc;
	
	
	private LocalDate docdate;
	
	
	private String authuser;
	
	
	private String descr;
	
	
	private String origcmpcode;
	
	
	private String origdoccode;
	
	
	private String origdocnum;
	@Id
	@Column(name = "\"LINKCODE\"")
	public String getLinkcode() {
		return linkcode;
	}
	public void setLinkcode(String linkcode) {
		this.linkcode = linkcode;
	}
	@Column(name = "\"CMPCODE\"")
	@Id
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
	@Column(name = "\"POSTED\"")
	public Integer getPosted() {
		return posted;
	}
	public void setPosted(Integer posted) {
		this.posted = posted;
	}
	@Column(name = "\"YR\"")
	public Integer getYr() {
		return yr;
	}
	public void setYr(Integer yr) {
		this.yr = yr;
	}
	@Column(name = "\"PERIOD\"")
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	@Column(name = "\"CURDOC\"")
	public String getCurdoc() {
		return curdoc;
	}
	public void setCurdoc(String curdoc) {
		this.curdoc = curdoc;
	}
	@Column(name = "\"DOCDATE\"")
	public LocalDate getDocdate() {
		return docdate;
	}
	public void setDocdate(LocalDate docdate) {
		this.docdate = docdate;
	}
	@Column(name = "\"AUTHUSER\"")
	public String getAuthuser() {
		return authuser;
	}
	public void setAuthuser(String authuser) {
		this.authuser = authuser;
	}
	@Column(name = "\"DESCR\"")
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Column(name = "\"ORIGCMPCODE\"")
	public String getOrigcmpcode() {
		return origcmpcode;
	}
	public void setOrigcmpcode(String origcmpcode) {
		this.origcmpcode = origcmpcode;
	}
	@Column(name = "\"ORIGDOCCODE\"")
	public String getOrigdoccode() {
		return origdoccode;
	}
	public void setOrigdoccode(String origdoccode) {
		this.origdoccode = origdoccode;
	}
	@Column(name = "\"ORIGDOCNUM\"")
	public String getOrigdocnum() {
		return origdocnum;
	}
	public void setOrigdocnum(String origdocnum) {
		this.origdocnum = origdocnum;
	}
	
	
}