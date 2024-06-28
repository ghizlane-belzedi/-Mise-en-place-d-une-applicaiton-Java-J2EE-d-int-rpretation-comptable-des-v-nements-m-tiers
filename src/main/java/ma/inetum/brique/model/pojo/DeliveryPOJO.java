package ma.inetum.brique.model.pojo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class DeliveryPOJO {

    @Column(name = "COMPID")
    private String compid;
    @Column(name = "OPERTYPE")
    private String opertype;
    @Column(name = "SELLLOCN")
    private String sellLocn;
    @Column(name = "INVOICE")
    private Long invNo;
    @Column(name = "INVDATE")
    private LocalDate invDate;
    @Column(name = "CRNNO")
    private Long crnNo;
    @Column(name = "CRNDATE")
    private  LocalDate crnDate;
    @Column(name = "CUSTORD")
    private  String custOrd;
    @Column(name = "CUSDATE")
    private LocalDate cusDate;
    @Column(name = "CUSTCODE")
    private Long custCode;
    @Column(name = "CUSTNAME")
    private String custName;
    @Column(name = "VEHICLE")
    private  Long vehicle;
    @Column(name = "CHASSIS")
    private String chassis;
    @Column(name= "FRAN")
    private	String	fran;
    @Column(name= "FRANDESC")
    private	String	frandesc;
    @Column(name = "MODEL")
    private String model;
    @Column(name= "MODDESC")
    private String moddesc;
    @Column(name= "VARIANT")
    private String variant;
    @Column(name= "VARDESC")
    private String vardesc;
    @Column(name = "REGN")
    private String regN;
    @Column(name = "SALENET")
    private Double salenet;
    @Column(name = "RECPRI1")
    private Double recpri1;
    @Column(name = "RECPRI2")
    private Double recpri2;
    @Column(name = "RECPRI3")
    private Double recpri3;
    @Column(name = "RECPRI4")
    private Double recpri4;
    @Column(name = "RECPRI5")
    private Double recpri5;
    @Column(name = "RECPRI6")
    private Double recpri6;
    @Column(name = "RECPRI7")
    private Double recpri7;
    @Column(name = "RECPRI8")
    private Double recpri8;
    @Column(name = "RECPRI9")
    private Double recpri9;
    @Column(name = "RECPRI10")
    private Double recpri10;
    @Column(name = "RECPRI11")
    private Double recpri11;
    @Column(name = "RECPRI12")
    private Double recpri12;
    @Column(name = "RECPRI13")
    private Double recpri13;
    @Column(name = "RECPRI14")
    private Double recpri14;
    @Column(name = "RECPRI15")
    private Double recpri15;
    @Column(name = "SALEVAT")
    private Double salevat;
    @Column(name = "LUXTAX")
    private Double luxtax;
    @Column(name = "INVALUE")
    private Double invalue;
    @Column(name = "STYPE")
    private String stype;
    @Column(name = "CREDAGNO")
    private String credAgNo;
    @Column(name = "VATEXNO")
    private String vatExNo;
    @Column(name = "VATEXDAT")
    private LocalDate vatExDat;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PROGRESS")
    private String progress;
    @Column(name = "DELDATE")
    private LocalDate delDate;
    @Column(name = "DELNOTE")
    private String delNote;
    @Column(name = "CREDATE")
    private LocalDate creDate;
    @Column(name = "USERID")
    private String userId;
    @Column(name = "TOTCOST")
    private Double totCost;
    @Column(name = "TOTNRCST")
    private Double totNrCst;
    @Column(name = "VEHCOST")
    private Double vehCost;
    @Column(name = "INVACC")
    private String invAcc;
    @Column(name = "INVNAME")
    private String invName;
    @Column(name = "CUSACC")
    private String cusAcc;
    @Column(name = "CUSNAME")
    private String cusName;

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }

    public String getOpertype() {
        return opertype;
    }

    public void setOpertype(String opertype) {
        this.opertype = opertype;
    }

    public String getSellLocn() {
        return sellLocn;
    }

    public void setSellLocn(String sellLocn) {
        this.sellLocn = sellLocn;
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

    public Long getCrnNo() {
        return crnNo;
    }

    public void setCrnNo(Long crnNo) {
        this.crnNo = crnNo;
    }

    public LocalDate getCrnDate() {
        return crnDate;
    }

    public void setCrnDate(LocalDate crnDate) {
        this.crnDate = crnDate;
    }

    public String getCustOrd() {
        return custOrd;
    }

    public void setCustOrd(String custOrd) {
        this.custOrd = custOrd;
    }

    public LocalDate getCusDate() {
        return cusDate;
    }

    public void setCusDate(LocalDate cusDate) {
        this.cusDate = cusDate;
    }

    public Long getCustCode() {
        return custCode;
    }

    public void setCustCode(Long custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getFran() {
        return fran;
    }

    public void setFran(String fran) {
        this.fran = fran;
    }

    public String getFrandesc() {
        return frandesc;
    }

    public void setFrandesc(String frandesc) {
        this.frandesc = frandesc;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModdesc() {
        return moddesc;
    }

    public void setModdesc(String moddesc) {
        this.moddesc = moddesc;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getVardesc() {
        return vardesc;
    }

    public void setVardesc(String vardesc) {
        this.vardesc = vardesc;
    }

    public String getRegN() {
        return regN;
    }

    public void setRegN(String regN) {
        this.regN = regN;
    }

    public Double getSalenet() {
        return salenet;
    }

    public void setSalenet(Double salenet) {
        this.salenet = salenet;
    }

    public Double getRecpri1() {
        return recpri1;
    }

    public void setRecpri1(Double recpri1) {
        this.recpri1 = recpri1;
    }

    public Double getRecpri2() {
        return recpri2;
    }

    public void setRecpri2(Double recpri2) {
        this.recpri2 = recpri2;
    }

    public Double getRecpri3() {
        return recpri3;
    }

    public void setRecpri3(Double recpri3) {
        this.recpri3 = recpri3;
    }

    public Double getRecpri4() {
        return recpri4;
    }

    public void setRecpri4(Double recpri4) {
        this.recpri4 = recpri4;
    }

    public Double getRecpri5() {
        return recpri5;
    }

    public void setRecpri5(Double recpri5) {
        this.recpri5 = recpri5;
    }

    public Double getRecpri6() {
        return recpri6;
    }

    public void setRecpri6(Double recpri6) {
        this.recpri6 = recpri6;
    }

    public Double getRecpri7() {
        return recpri7;
    }

    public void setRecpri7(Double recpri7) {
        this.recpri7 = recpri7;
    }

    public Double getRecpri8() {
        return recpri8;
    }

    public void setRecpri8(Double recpri8) {
        this.recpri8 = recpri8;
    }

    public Double getRecpri9() {
        return recpri9;
    }

    public void setRecpri9(Double recpri9) {
        this.recpri9 = recpri9;
    }

    public Double getRecpri10() {
        return recpri10;
    }

    public void setRecpri10(Double recpri10) {
        this.recpri10 = recpri10;
    }

    public Double getRecpri11() {
        return recpri11;
    }

    public void setRecpri11(Double recpri11) {
        this.recpri11 = recpri11;
    }

    public Double getRecpri12() {
        return recpri12;
    }

    public void setRecpri12(Double recpri12) {
        this.recpri12 = recpri12;
    }

    public Double getRecpri13() {
        return recpri13;
    }

    public void setRecpri13(Double recpri13) {
        this.recpri13 = recpri13;
    }

    public Double getRecpri14() {
        return recpri14;
    }

    public void setRecpri14(Double recpri14) {
        this.recpri14 = recpri14;
    }

    public Double getRecpri15() {
        return recpri15;
    }

    public void setRecpri15(Double recpri15) {
        this.recpri15 = recpri15;
    }

    public Double getSalevat() {
        return salevat;
    }

    public void setSalevat(Double salevat) {
        this.salevat = salevat;
    }

    public Double getLuxtax() {
        return luxtax;
    }

    public void setLuxtax(Double luxtax) {
        this.luxtax = luxtax;
    }

    public Double getInvalue() {
        return invalue;
    }

    public void setInvalue(Double invalue) {
        this.invalue = invalue;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getCredAgNo() {
        return credAgNo;
    }

    public void setCredAgNo(String credAgNo) {
        this.credAgNo = credAgNo;
    }

    public String getVatExNo() {
        return vatExNo;
    }

    public void setVatExNo(String vatExNo) {
        this.vatExNo = vatExNo;
    }

    public LocalDate getVatExDat() {
        return vatExDat;
    }

    public void setVatExDat(LocalDate vatExDat) {
        this.vatExDat = vatExDat;
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

    public LocalDate getDelDate() {
        return delDate;
    }

    public void setDelDate(LocalDate delDate) {
        this.delDate = delDate;
    }

    public String getDelNote() {
        return delNote;
    }

    public void setDelNote(String delNote) {
        this.delNote = delNote;
    }

    public LocalDate getCreDate() {
        return creDate;
    }

    public void setCreDate(LocalDate creDate) {
        this.creDate = creDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getTotCost() {
        return totCost;
    }

    public void setTotCost(Double totCost) {
        this.totCost = totCost;
    }

    public Double getTotNrCst() {
        return totNrCst;
    }

    public void setTotNrCost(Double totNrCst) {
        this.totNrCst = totNrCst;
    }

    public Double getVehCost() {
        return vehCost;
    }

    public void setVehCost(Double vehCost) {
        this.vehCost = vehCost;
    }

    public String getInvAcc() {
        return invAcc;
    }

    public void setInvAcc(String invAcc) {
        this.invAcc = invAcc;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    public String getCusAcc() {
        return cusAcc;
    }

    public void setCusAcc(String cusAcc) {
        this.cusAcc = cusAcc;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }
}
