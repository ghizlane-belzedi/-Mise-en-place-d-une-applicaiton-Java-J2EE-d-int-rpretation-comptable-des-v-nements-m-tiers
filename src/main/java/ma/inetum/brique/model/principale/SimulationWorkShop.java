package ma.inetum.brique.model.principale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SIMULATION_CDPOH")
public class SimulationWorkShop{

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "HEADER_ID")
	private WorkShopHeader header;
	@ManyToOne
	@JoinColumn(name = "SIMULATION_ID")
	private Simulation simulation;
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
	public Simulation getSimulation() {
		return simulation;
	}
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
	public SimulationWorkShop(WorkShopHeader header, Simulation simulation) {
		super();
		this.header = header;
		this.simulation = simulation;
	} 
	
}
