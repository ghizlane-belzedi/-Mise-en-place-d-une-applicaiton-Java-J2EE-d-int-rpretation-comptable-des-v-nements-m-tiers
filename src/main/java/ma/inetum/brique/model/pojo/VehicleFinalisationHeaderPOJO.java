package ma.inetum.brique.model.pojo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
public class VehicleFinalisationHeaderPOJO {
    @Column(name= "COMPID")
    private	String	compid;
    @Column(name= "SHIPNO")
    private	String	shipno;
    @Column(name= "SHIPDATE")
    private Date shipdate;
    @Column(name= "SHIPNAME")
    private	String	shipname;
    @Column(name= "PINVNO")
    private	String	pinvno;
    @Column(name= "SUPPLIER")
    private	String	supplier;
    @Column(name= "PINVDATE")
    private	Date	pinvdate;
    @Column(name= "SUPACC")
    private	String	supacc;
    @Column(name= "FRAN")
    private	String	fran;
    @Column(name= "FRANDESC")
    private	String	frandesc;
    @Column(name= "IMFILENO")
    private String imfileNo;
    @Column(name= "IMCOMMNO")
    private String imcommno;
    @Column(name = "CREDATE")
    private LocalDate credate;
    @Column(name = "USERID")
    private String userId;

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

    public Date getShipdate() {
        return shipdate;
    }

    public void setShipdate(Date shipdate) {
        this.shipdate = shipdate;
    }

    public String getShipname() {
        return shipname;
    }

    public void setShipname(String shipname) {
        this.shipname = shipname;
    }

    public String getPinvno() {
        return pinvno;
    }

    public void setPinvno(String pinvno) {
        this.pinvno = pinvno;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getPinvdate() {
        return pinvdate;
    }

    public void setPinvdate(Date pinvdate) {
        this.pinvdate = pinvdate;
    }

    public String getSupacc() {
        return supacc;
    }

    public void setSupacc(String supacc) {
        this.supacc = supacc;
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

    public String getImfileNo() {
        return imfileNo;
    }

    public void setImfileNo(String imfileNo) {
        this.imfileNo = imfileNo;
    }

    public String getImcommno() {
        return imcommno;
    }

    public void setImcommno(String imcommno) {
        this.imcommno = imcommno;
    }

    public LocalDate getCredate() {return credate;}

    public void setCredate(LocalDate credate) {this.credate = credate;}

    public String getUserId() {return userId;}

    public void setUserId(String userId) {this.userId = userId;}
}
