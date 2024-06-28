package ma.inetum.brique.model.principale;

import javax.persistence.*;


@Entity
@Table(name = "SIMULATION_CDFIH")
public class SimulationVehicleFinalisation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "HEADER_ID")
    private VehicleFinalisationHeaderP vehicleFinalisationHeaderP;

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID")
    private Simulation simulation;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public VehicleFinalisationHeaderP getVehicleFinalisationHeaderP() {return vehicleFinalisationHeaderP;}

    public void setVehicleFinalisationHeaderP(VehicleFinalisationHeaderP vehicleFinalisationHeaderP) {
        this.vehicleFinalisationHeaderP = vehicleFinalisationHeaderP;
    }

    public Simulation getSimulation() {return simulation;}

    public void setSimulation(Simulation simulation) {this.simulation = simulation;}

    public SimulationVehicleFinalisation(VehicleFinalisationHeaderP vehicleFinalisationHeaderP, Simulation simulation){
        super();
        this.vehicleFinalisationHeaderP = vehicleFinalisationHeaderP;
        this.simulation = simulation;
    }
}

