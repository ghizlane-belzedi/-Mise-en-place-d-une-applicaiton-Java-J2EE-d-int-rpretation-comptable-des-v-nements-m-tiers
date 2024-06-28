package ma.inetum.brique.model.principale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SIMULATION_CDDEP")
public class SimulationDeposit {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "VEHICULE_ID")
	private Deposit deposit;
	@ManyToOne
	@JoinColumn(name = "SIMULATION_ID")
	private Simulation simulation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Deposit getDeposit() {
		return deposit;
	}
	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
	public SimulationDeposit(Deposit deposit, Simulation simulation) {
		super();
		this.deposit = deposit;
		this.simulation = simulation;
	} 
	
}
