package ma.inetum.brique.model.principale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ma.inetum.brique.model.pojo.WorkShopDetailsPOJO;

@Entity
@Table(name = "CS_ME_CDPOL")
public class WorkShopDetails extends WorkShopDetailsPOJO {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "HEADER_ID")
	private WorkShopHeader header;
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
	public Long getMagic() {
		return magic;
	}
	public void setMagic(Long magic) {
		this.magic = magic;
	}
	
}
