package ma.inetum.brique.model.principale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SIMULATION_CDCOH")
public class SimulationCosting {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "HEADER_ID")
	private CostingHeader header;
	@ManyToOne
	@JoinColumn(name = "SIMULATION_ID")
	private Simulation simulation;
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
	public Simulation getSimulation() {
		return simulation;
	}
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
	public SimulationCosting(CostingHeader header, Simulation simulation) {
		super();
		this.header = header;
		this.simulation = simulation;
	} 
	
}
