package ma.inetum.brique.model.metier;

import ma.inetum.brique.model.pojo.VehicleFinalisationDetailsPOJO;

import javax.persistence.*;

@Entity
@Table(name = "V_CS_ME_CDFIL")
@IdClass(VehicleFinalisationDetailsId.class)
public class VehicleFinalisationDetails extends VehicleFinalisationDetailsPOJO {
    @Id
    private VehicleFinalisationHeader vehicleFinalisationHeader;
    @Id
    private Double vehicle;
    @Id
    private String chassis;
    @Id
    private String compid;



    public VehicleFinalisationHeader getVehicleFinalisationHeader() {
        return vehicleFinalisationHeader;
    }

    public void setVehicleFinalisationHeader(VehicleFinalisationHeader vehicleFinalisationHeader) {
        this.vehicleFinalisationHeader = vehicleFinalisationHeader;
    }

    public Double getVehicle() {
        return vehicle;
    }
    public void setVehicle(Double vehicle) {
        this.vehicle = vehicle;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getCompid() {return compid;}

    public void setCompid(String compid) {this.compid = compid;}
}
