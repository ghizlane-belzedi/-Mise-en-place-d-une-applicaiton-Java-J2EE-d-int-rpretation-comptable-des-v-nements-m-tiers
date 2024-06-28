package ma.inetum.brique.model.metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import ma.inetum.brique.model.pojo.CostingDetailsPOJO;

@Entity
@Table(name = "V_CS_ME_CDCOL")
@IdClass(CostingDetailsId.class)
public class CostingDetails extends CostingDetailsPOJO {
	@Id
	private CostingHeader header;
	@Id
	private Long vehicle;
	@Id
	@Column(length = 30)
	private String chassis;
	@Id
	private String fran;
	@Id
	@Column(length = 1)
	private String analysis;
	
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
	
	
}
