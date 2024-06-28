package ma.inetum.brique.model.metier;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import ma.inetum.brique.model.pojo.WorkShopDetailsPOJO;

@Entity
@Table(name = "V_CS_ME_CDPOL")
@IdClass(WorkShopDetailsId.class)
public class WorkShopDetails extends WorkShopDetailsPOJO {
	@Id
	private WorkShopHeader header;
	@Id
	private Long vehicle;
	@Id
	private String chassis;
	@Id
	private String fran;
	@Id
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
