package ma.inetum.brique.model.coda;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "OAS_LINKLINE")
@IdClass(LinkLineId.class)
public class LinkLine {

	@Id
	@Column(name = "LINKCODE")
	private String linkcode;
	@Id
	@Column(name = "CMPCODE")
	private String cmpcode;
	@Id
	@Column(name = "DOCCODE")
	private String doccode;
	@Id
	@Column(name = "DOCNUM")
	private Long docnum;
	@Id
	@Column(name = "DOCLINENUM")
	private Integer doclinenum;

	@Column(name = "ACCODE")
	private String accode;
	@Column(name = "VALUEDOC")
	private Double valuedoc;
	@Column(name = "VALUEDOC_DP")
	private Integer valuedoc_dp;
	@Column(name = "DOCRATE")
	private Double docrate;
	@Column(name = "VALUEHOME")
	private Double valuehome;
	@Column(name = "VALUEHOME_DP")
	private Integer valuehome_dp;
	@Column(name = "VALUEDUAL")
	private Double valuedual;
	@Column(name = "VALUEDUAL_DP")
	private Integer valuedual_dp;
	@Column(name = "DUALRATE")
	private Double dualrate;
	@Column(name = "STATUSER")
	private Character statuser;
	@Column(name = "LINETYPE")
	private Integer linetype;
	@Column(name = "DEBITCREDIT")
	private Integer debitcredit;
	@Column(name = "DUEDATE")
	private LocalDateTime duedate;
	@Column(name = "VALDATE")
	private LocalDateTime valdate;

	@Column(name = "TAXCODE9")
	private String taxcode;
	@Column(name = "TAXVALUE9")
	private Double taxvalue;
	@Column(name = "TAXVALUE9_DP")
	private Integer taxvalue_dp;
	@Column(name = "DESCR")
	private String descr;

	@Column(name = "EXTREF1")
	private String extref1;
	@Column(name = "EXTREF2")
	private String extref2;
	@Column(name = "EXTREF3")
	private String extref3;
	@Column(name = "EXTREF4")
	private String extref4;
	@Column(name = "EXTREF5")
	private String extref5;
	@Column(name = "EXTREF6")
	private String extref6;

	@Column(name = "ELCURR1")
	private String elcurr1;

	@Column(name = "ELVALUE1")
	private Double elvalue1;

	@Column(name = "ELVALUE1_DP")
	private Integer elvalue1_dp;

	@Column(name = "ELRATE1")
	private Double elrate1;
	
	@Column(name = "ELCURR2")
	private String elcurr2;

	@Column(name = "ELVALUE2")
	private Double elvalue2;

	@Column(name = "ELVALUE2_DP")
	private Integer elvalue2_dp;

	@Column(name = "ELRATE2")
	private Double elrate2;

	@Column(name = "ELCURR8")
	private String elcurr8;

	@Column(name = "ELVALUE8")
	private Double elvalue8;

	@Column(name = "ELVALUE8_DP")
	private Integer elvalue8_dp;

	@Column(name = "ELRATE8")
	private Double elrate8;

	@Column(name = "DISCSFLAG")

	private Integer discsflag;

	@Column(name = "DISCDATE5")
	private LocalDateTime discdate;

	@Column(name = "DISCRATE5")

	private Double discrate;

	@Column(name = "DISCVALUE5")

	private Double discvalue;

	@Column(name = "DISCVALUE5_DP")

	private Integer discvalue_dp;

	@Column(name = "DOCSUMTAX")
	private Double docsumtax;
	@Column(name = "DOCSUMTAX_DP")
	private Integer docsumtax_dp;
	@Column(name = "TAXLINECODE")
	private String taxlinecode;

	@Column(name = "DOCTAXTURN")
	private Double doctaxturn;
	@Column(name = "DOCTAXTURN_DP")
	private Integer doctaxturn_dp;

	@Column(name = "TEN99TAXCODE3")
	private String ten99taxcode;

	@Column(name = "TEN99TAXVALUE3")

	private Double ten99taxvalue;

	@Column(name = "TEN99TAXVALUE3_DP")

	private Integer ten99taxvalue_dp;

	@Column(name = "MEDCODE")

	private String medcode;
	@Column(name = "BNKCODE")
	private String bnkcode;
	@Column(name = "ELMBANKTAG")
	private Integer elmbanktag;
	@Column(name = "ELMADDRTAG")
	private Integer elmaddrtag;

	@Column(name = "USRREF3")
	private String usrref;

	@Column(name = "DESTCODE")

	private String destcode;
	@Column(name = "PARENTCURR")
	private String parentcurr;
	@Column(name = "PARENTVALUE")
	private Double parentvalue;
	@Column(name = "PARENTVALUE_DP")
	private Integer parentvalue_dp;
	@Column(name = "PARENTRATE")
	private Double parentrate;
	
	@Column(name = "ASSETCODE")
	private String assetcode;
	@Column(name = "CATCODE")
	private String catcode;
	@Column(name = "ASSETFLAGS")
	private Integer assetflags;
	@Column(name = "CAPDATE")
	private LocalDateTime capdate;
	@Column(name = "DEPNSTARTDATE")
	private LocalDateTime depnstartdate;
	public String getLinkcode() {
		return linkcode;
	}
	public void setLinkcode(String linkcode) {
		this.linkcode = linkcode;
	}
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
	public String getAccode() {
		return accode;
	}
	public void setAccode(String accode) {
		this.accode = accode;
	}
	public Double getValuedoc() {
		return valuedoc;
	}
	public void setValuedoc(Double valuedoc) {
		this.valuedoc = valuedoc;
	}
	public Integer getValuedoc_dp() {
		return valuedoc_dp;
	}
	public void setValuedoc_dp(Integer valuedoc_dp) {
		this.valuedoc_dp = valuedoc_dp;
	}
	public Double getDocrate() {
		return docrate;
	}
	public void setDocrate(Double docrate) {
		this.docrate = docrate;
	}
	public Double getValuehome() {
		return valuehome;
	}
	public void setValuehome(Double valuehome) {
		this.valuehome = valuehome;
	}
	public Integer getValuehome_dp() {
		return valuehome_dp;
	}
	public void setValuehome_dp(Integer valuehome_dp) {
		this.valuehome_dp = valuehome_dp;
	}
	public Double getValuedual() {
		return valuedual;
	}
	public void setValuedual(Double valuedual) {
		this.valuedual = valuedual;
	}
	public Integer getValuedual_dp() {
		return valuedual_dp;
	}
	public void setValuedual_dp(Integer valuedual_dp) {
		this.valuedual_dp = valuedual_dp;
	}
	public Double getDualrate() {
		return dualrate;
	}
	public void setDualrate(Double dualrate) {
		this.dualrate = dualrate;
	}
	public Character getStatuser() {
		return statuser;
	}
	public void setStatuser(Character statuser) {
		this.statuser = statuser;
	}
	public Integer getLinetype() {
		return linetype;
	}
	public void setLinetype(Integer linetype) {
		this.linetype = linetype;
	}
	public Integer getDebitcredit() {
		return debitcredit;
	}
	public void setDebitcredit(Integer debitcredit) {
		this.debitcredit = debitcredit;
	}
	public LocalDateTime getDuedate() {
		return duedate;
	}
	public void setDuedate(LocalDateTime duedate) {
		this.duedate = duedate;
	}
	public LocalDateTime getValdate() {
		return valdate;
	}
	public void setValdate(LocalDateTime valdate) {
		this.valdate = valdate;
	}
	public String getTaxcode() {
		return taxcode;
	}
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}
	public Double getTaxvalue() {
		return taxvalue;
	}
	public void setTaxvalue(Double taxvalue) {
		this.taxvalue = taxvalue;
	}
	public Integer getTaxvalue_dp() {
		return taxvalue_dp;
	}
	public void setTaxvalue_dp(Integer taxvalue_dp) {
		this.taxvalue_dp = taxvalue_dp;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getExtref6() {
		return extref6;
	}
	public void setExtref6(String extref) {
		this.extref6 = extref;
	}
	public Integer getDiscsflag() {
		return discsflag;
	}
	public void setDiscsflag(Integer discsflag) {
		this.discsflag = discsflag;
	}
	public LocalDateTime getDiscdate() {
		return discdate;
	}
	public void setDiscdate(LocalDateTime discdate) {
		this.discdate = discdate;
	}
	public Double getDiscrate() {
		return discrate;
	}
	public void setDiscrate(Double discrate) {
		this.discrate = discrate;
	}
	public Double getDiscvalue() {
		return discvalue;
	}
	public void setDiscvalue(Double discvalue) {
		this.discvalue = discvalue;
	}
	public Integer getDiscvalue_dp() {
		return discvalue_dp;
	}
	public void setDiscvalue_dp(Integer discvalue_dp) {
		this.discvalue_dp = discvalue_dp;
	}
	public Double getDocsumtax() {
		return docsumtax;
	}
	public void setDocsumtax(Double docsumtax) {
		this.docsumtax = docsumtax;
	}
	public Integer getDocsumtax_dp() {
		return docsumtax_dp;
	}
	public void setDocsumtax_dp(Integer docsumtax_dp) {
		this.docsumtax_dp = docsumtax_dp;
	}
	public String getTaxlinecode() {
		return taxlinecode;
	}
	public void setTaxlinecode(String taxlinecode) {
		this.taxlinecode = taxlinecode;
	}
	public Double getDoctaxturn() {
		return doctaxturn;
	}
	public void setDoctaxturn(Double doctaxturn) {
		this.doctaxturn = doctaxturn;
	}
	public Integer getDoctaxturn_dp() {
		return doctaxturn_dp;
	}
	public void setDoctaxturn_dp(Integer doctaxturn_dp) {
		this.doctaxturn_dp = doctaxturn_dp;
	}
	public String getTen99taxcode() {
		return ten99taxcode;
	}
	public void setTen99taxcode(String ten99taxcode) {
		this.ten99taxcode = ten99taxcode;
	}
	public Double getTen99taxvalue() {
		return ten99taxvalue;
	}
	public void setTen99taxvalue(Double ten99taxvalue) {
		this.ten99taxvalue = ten99taxvalue;
	}
	public Integer getTen99taxvalue_dp() {
		return ten99taxvalue_dp;
	}
	public void setTen99taxvalue_dp(Integer ten99taxvalue_dp) {
		this.ten99taxvalue_dp = ten99taxvalue_dp;
	}
	public String getMedcode() {
		return medcode;
	}
	public void setMedcode(String medcode) {
		this.medcode = medcode;
	}
	public String getBnkcode() {
		return bnkcode;
	}
	public void setBnkcode(String bnkcode) {
		this.bnkcode = bnkcode;
	}
	public Integer getElmbanktag() {
		return elmbanktag;
	}
	public void setElmbanktag(Integer elmbanktag) {
		this.elmbanktag = elmbanktag;
	}
	public Integer getElmaddrtag() {
		return elmaddrtag;
	}
	public void setElmaddrtag(Integer elmaddrtag) {
		this.elmaddrtag = elmaddrtag;
	}
	public String getUsrref() {
		return usrref;
	}
	public void setUsrref(String usrref) {
		this.usrref = usrref;
	}
	public String getDestcode() {
		return destcode;
	}
	public void setDestcode(String destcode) {
		this.destcode = destcode;
	}
	public String getParentcurr() {
		return parentcurr;
	}
	public void setParentcurr(String parentcurr) {
		this.parentcurr = parentcurr;
	}
	public Double getParentvalue() {
		return parentvalue;
	}
	public void setParentvalue(Double parentvalue) {
		this.parentvalue = parentvalue;
	}
	public Integer getParentvalue_dp() {
		return parentvalue_dp;
	}
	public void setParentvalue_dp(Integer parentvalue_dp) {
		this.parentvalue_dp = parentvalue_dp;
	}
	public Double getParentrate() {
		return parentrate;
	}
	public void setParentrate(Double parentrate) {
		this.parentrate = parentrate;
	}
	public String getAssetcode() {
		return assetcode;
	}
	public void setAssetcode(String assetcode) {
		this.assetcode = assetcode;
	}
	public String getCatcode() {
		return catcode;
	}
	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}
	public Integer getAssetflags() {
		return assetflags;
	}
	public void setAssetflags(Integer assetflags) {
		this.assetflags = assetflags;
	}
	public LocalDateTime getCapdate() {
		return capdate;
	}
	public void setCapdate(LocalDateTime capdate) {
		this.capdate = capdate;
	}
	public LocalDateTime getDepnstartdate() {
		return depnstartdate;
	}
	public void setDepnstartdate(LocalDateTime depnstartdate) {
		this.depnstartdate = depnstartdate;
	}
	public Double getElvalue1() {
		return elvalue1;
	}
	public void setElvalue1(Double elvalue1) {
		this.elvalue1 = elvalue1;
	}
	public Integer getElvalue1_dp() {
		return elvalue1_dp;
	}
	public void setElvalue1_dp(Integer elvalue1_dp) {
		this.elvalue1_dp = elvalue1_dp;
	}
	public Double getElrate1() {
		return elrate1;
	}
	public void setElrate1(Double elrate1) {
		this.elrate1 = elrate1;
	}
	public Double getElvalue2() {
		return elvalue2;
	}
	public void setElvalue2(Double elvalue2) {
		this.elvalue2 = elvalue2;
	}
	public Integer getElvalue2_dp() {
		return elvalue2_dp;
	}
	public void setElvalue2_dp(Integer elvalue2_dp) {
		this.elvalue2_dp = elvalue2_dp;
	}
	public Double getElrate2() {
		return elrate2;
	}
	public void setElrate2(Double elrate2) {
		this.elrate2 = elrate2;
	}
	public Double getElvalue8() {
		return elvalue8;
	}
	public void setElvalue8(Double elvalue8) {
		this.elvalue8 = elvalue8;
	}
	public Integer getElvalue8_dp() {
		return elvalue8_dp;
	}
	public void setElvalue8_dp(Integer elvalue8_dp) {
		this.elvalue8_dp = elvalue8_dp;
	}
	public Double getElrate8() {
		return elrate8;
	}
	public void setElrate8(Double elrate8) {
		this.elrate8 = elrate8;
	}
	public String getElcurr1() {
		return elcurr1;
	}
	public void setElcurr1(String elcurr1) {
		this.elcurr1 = elcurr1;
	}
	public String getElcurr2() {
		return elcurr2;
	}
	public void setElcurr2(String elcurr2) {
		this.elcurr2 = elcurr2;
	}
	public String getElcurr8() {
		return elcurr8;
	}
	public void setElcurr8(String elcurr8) {
		this.elcurr8 = elcurr8;
	}
	public String getExtref1() {
		return extref1;
	}
	public void setExtref1(String extref1) {
		this.extref1 = extref1;
	}
	public String getExtref2() {
		return extref2;
	}
	public void setExtref2(String extref2) {
		this.extref2 = extref2;
	}
	public String getExtref3() {
		return extref3;
	}
	public void setExtref3(String extref3) {
		this.extref3 = extref3;
	}
	public String getExtref4() {
		return extref4;
	}
	public void setExtref4(String extref4) {
		this.extref4 = extref4;
	}
	public String getExtref5() {
		return extref5;
	}
	public void setExtref5(String extref5) {
		this.extref5 = extref5;
	}
}
