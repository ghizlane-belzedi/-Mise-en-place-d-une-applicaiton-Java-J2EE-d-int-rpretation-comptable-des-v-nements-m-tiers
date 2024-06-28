package ma.inetum.brique.model.pojo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class LogisticOperationsPOJO {

    @Column(name = "COMPID")
    private String compid;

    @Column(name = "OPERTYPE")
    private String opertype;

    @Column(name = "VEHICLE")
    private Long vehicle;

    @Column(name = "CHASSIS")
    private String chassis;

    @Column(name = "FRAN")
    private String fran;

    @Column(name = "FRANDESC")
    private String frandesc;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "MODDESC")
    private String moddesc;

    @Column(name = "VARIANT")
    private String variant;

    @Column(name = "VARDESC")
    private String vardesc;

    @Column(name = "COST")
    private Double cost;

    @Column(name = "BUYLOC")
    private String buyloc;

    @Column(name = "PHYLOC")
    private String phyloc;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PROGRESS")
    private String progress;

    @Column(name = "LOCN")
    private String locn;

    @Column(name = "LOCNNEW")
    private String Locnnew;

    @Column(name = "COMM")
    private String comm;

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

    public String getOpertype() {
        return opertype;
    }

    public void setOpertype(String opertype) {
        this.opertype = opertype;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getBuyloc() {
        return buyloc;
    }

    public void setBuyloc(String buyloc) {
        this.buyloc = buyloc;
    }

    public String getPhyloc() {
        return phyloc;
    }

    public void setPhyloc(String phyloc) {
        this.phyloc = phyloc;
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

    public String getLocn() {
        return locn;
    }

    public void setLocn(String locn) {
        this.locn = locn;
    }

    public String getLocnnew() {
        return Locnnew;
    }

    public void setLocnnew(String locnnew) {
        Locnnew = locnnew;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public LocalDate getCredate() {
        return credate;
    }

    public void setCredate(LocalDate credate) {
        this.credate = credate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
