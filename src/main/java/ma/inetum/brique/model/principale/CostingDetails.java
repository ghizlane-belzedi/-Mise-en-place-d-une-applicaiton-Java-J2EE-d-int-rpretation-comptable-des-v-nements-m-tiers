package ma.inetum.brique.model.principale;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ma.inetum.brique.model.pojo.CostingDetailsPOJO;

@Entity
@Table(name = "CS_ME_CDCOL")
public class CostingDetails extends CostingDetailsPOJO{
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "HEADER_ID")
	private CostingHeader header;
	@Column(name = "MAGIC")
	private Long magic;
	@Column(name = "VEHICLE")
	private Long vehicle;
	@Column(name = "CHASSIS")
	private String chassis;
	@Column(name = "FRAN")
	private String fran;
	@Column(name = "ANALYSIS")
	private String analysis;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public CostingHeader getHeader() {
		return header;
	}
	public void setHeader(CostingHeader header) {
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
	public Long getMagic() {
		return magic;
	}
	public void setMagic(Long magic) {
		this.magic = magic;
	}
	@Override
	public int hashCode() {
		return Objects.hash(analysis, chassis, fran, magic, vehicle);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CostingDetails other = (CostingDetails) obj;
		return Objects.equals(analysis, other.analysis) && Objects.equals(chassis, other.chassis)
				&& Objects.equals(fran, other.fran) && Objects.equals(magic, other.magic)
				&& Objects.equals(vehicle, other.vehicle);
	}
	
}
