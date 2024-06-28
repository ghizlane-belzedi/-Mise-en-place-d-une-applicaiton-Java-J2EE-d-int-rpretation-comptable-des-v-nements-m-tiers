package ma.inetum.brique.model.principale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SIMULATION_CDORD")
public class SimulationVehiculeOrder {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "VEHICULE_ID")
	private VehiculeOrder vehicule;
	@ManyToOne
	@JoinColumn(name = "SIMULATION_ID")
	private Simulation simulation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VehiculeOrder getVehicule() {
		return vehicule;
	}
	public void setVehicule(VehiculeOrder vehicule) {
		this.vehicule = vehicule;
	}
	public Simulation getSimulation() {
		return simulation;
	}
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
	public SimulationVehiculeOrder(VehiculeOrder vehicule, Simulation simulation) {
		super();
		this.vehicule = vehicule;
		this.simulation = simulation;
	} 
	
}
