package ma.inetum.brique.model.principale;

import ma.inetum.brique.model.pojo.VehicleFinalisationDetailsPOJO;

import javax.persistence.*;

@Entity
@Table(name = "CS_ME_CDFIL")
public class VehicleFinalisationDetailsP extends VehicleFinalisationDetailsPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "VEHICLE_FIN_DETAIL_SEQ")
    @SequenceGenerator(name = "VEHICLE_FIN_DETAIL_SEQ", sequenceName = "VEHICLE_FIN_DETAIL_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HEADER_ID")
    private VehicleFinalisationHeaderP vehicleFinalisationHeader;

    @Column(name = "MAGIC")
    private Long magic;
    @Column(name = "VEHICLE")
    private Integer vehicle;
    @Column(name = "CHASSIS")
    private String chassis;
    @Column(name= "COMPID")
    private String compid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleFinalisationHeaderP getVehicleFinalisationHeader() {
        return vehicleFinalisationHeader;
    }

    public void setVehicleFinalisationHeader(VehicleFinalisationHeaderP vehicleFinalisationHeader) {
        this.vehicleFinalisationHeader = vehicleFinalisationHeader;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public Long getMagic() {
        return magic;
    }

    public void setMagic(Long magic) {
        this.magic = magic;
    }

    public String getCompid() {return compid;}

    public void setCompid(String compid) {this.compid = compid;}
}