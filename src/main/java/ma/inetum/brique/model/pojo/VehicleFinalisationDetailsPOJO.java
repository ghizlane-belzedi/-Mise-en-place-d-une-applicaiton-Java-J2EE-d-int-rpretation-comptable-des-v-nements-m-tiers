package ma.inetum.brique.model.pojo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class VehicleFinalisationDetailsPOJO {
    @Column(name= "MODDESC")
    private String moddesc;
    @Column(name= "VARIANT")
    private String variant;
    @Column(name= "VARDESC")
    private String vardesc;
    @Column(name= "COLCODE")
    private String colcode;
    @Column(name= "COLDESC")
    private String coldesc;
    @Column(name= "TRIMCODE")
    private String trimcode;
    @Column(name= "TRIMDESC")
    private String trimdesc;
    @Column(name= "COMM")
    private String comm;
    @Column(name= "CONS")
    private Double cons;
    @Column(name= "FREIGHT")
    private Double freight;
    @Column(name= "BUYCURR")
    private String buycurr;
    @Column(name= "BUYEXCH")
    private Double buyexch;
    @Column(name= "FINDATE")
    private LocalDate findate;
    @Column(name = "FRAN")
    private String fran;
    @Column(name = "FRANDESC")
    private String frandesc;

    @Column(name = "MODEL")
    private String model;



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

    public String getColcode() {
        return colcode;
    }

    public void setColcode(String colcode) {
        this.colcode = colcode;
    }

    public String getColdesc() {
        return coldesc;
    }

    public void setColdesc(String coldesc) {
        this.coldesc = coldesc;
    }

    public String getTrimcode() {
        return trimcode;
    }

    public void setTrimcode(String trimcode) {
        this.trimcode = trimcode;
    }

    public String getTrimdesc() {
        return trimdesc;
    }

    public void setTrimdesc(String trimdesc) {
        this.trimdesc = trimdesc;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public Double getCons() {
        return cons;
    }

    public void setCons(Double cons) {
        this.cons = cons;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public String getBuycurr() {
        return buycurr;
    }

    public void setBuycurr(String buycurr) {
        this.buycurr = buycurr;
    }

    public Double getBuyexch() {
        return buyexch;
    }

    public void setBuyexch(Double buyexch) {
        this.buyexch = buyexch;
    }

    public LocalDate getFindate() {
        return findate;
    }

    public void setFindate(LocalDate findate) {
        this.findate = findate;
    }

    public String getFran() {return fran;}

    public void setFran(String fran) {this.fran = fran;}

    public String getFrandesc() {return frandesc;}

    public void setFrandesc(String frandesc) {this.frandesc = frandesc;}

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}
}
