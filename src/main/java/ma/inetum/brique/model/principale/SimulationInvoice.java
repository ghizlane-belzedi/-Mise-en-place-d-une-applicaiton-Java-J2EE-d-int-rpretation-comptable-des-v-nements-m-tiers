package ma.inetum.brique.model.principale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SIMULATION_CDINV")
public class SimulationInvoice {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "VEHICULE_ID")
	private Invoice invoice;
	@ManyToOne
	@JoinColumn(name = "SIMULATION_ID")
	private Simulation simulation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Simulation getSimulation() {
		return simulation;
	}
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
	public SimulationInvoice(Invoice invoice, Simulation simulation) {
		super();
		this.invoice = invoice;
		this.simulation = simulation;
	}
	public SimulationInvoice() {
	} 
	
}
