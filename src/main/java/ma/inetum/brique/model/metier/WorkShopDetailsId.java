package ma.inetum.brique.model.metier;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
class WorkShopDetailsId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2024310919388143773L;
	@Override
	public int hashCode() {
		return Objects.hash(analysis, chassis, fran, header, vehicle);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkShopDetailsId other = (WorkShopDetailsId) obj;
		return Objects.equals(analysis, other.analysis) && Objects.equals(chassis, other.chassis)
				&& Objects.equals(fran, other.fran) && Objects.equals(header, other.header)
				&& Objects.equals(vehicle, other.vehicle);
	}
	@ManyToOne
	@JoinColumn(name = "MAGIC")
	private WorkShopHeader header;
	@Column(name = "VEHICLE")
	private Long vehicle;
	@Column(name = "CHASSIS", length = 30)
	private String chassis;
	@Column(name = "FRAN",length = 5)
	private String fran;
	@Column(name = "ANALYSIS", length = 1)
	private String analysis;
	
	
	public WorkShopHeader getHeader() {
		return header;
	}
	public void setHeader(WorkShopHeader header) {
		this.header = header;
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
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	
}