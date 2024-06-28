package ma.inetum.brique.model.metier;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

class VehicleFinalisationDetailsId implements Serializable {
    private static final long serialVersionUID = 2024310919388143773L;
    @ManyToOne
    @JoinColumn(name = "MAGIC")
    private VehicleFinalisationHeader vehicleFinalisationHeader;
    private Double vehicle;
    private String chassis;
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

	@Override
	public int hashCode() {
		return Objects.hash(chassis, compid, vehicle, vehicleFinalisationHeader);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleFinalisationDetailsId other = (VehicleFinalisationDetailsId) obj;
		return Objects.equals(chassis, other.chassis) && Objects.equals(compid, other.compid)
				&& Objects.equals(vehicle, other.vehicle)
				&& Objects.equals(vehicleFinalisationHeader, other.vehicleFinalisationHeader);
	}
}
