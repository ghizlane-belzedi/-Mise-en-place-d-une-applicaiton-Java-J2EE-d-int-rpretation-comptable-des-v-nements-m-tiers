package ma.inetum.brique.model.principale;

import javax.persistence.*;

@Entity
@Table(name = "SIMULATION_CDMOV")
public class SimulationLogisticOperations {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LOGISTIC_OPERATIONS_ID")
    private LogisticOperationsP logisticOperationsP;

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID")
    private Simulation simulation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LogisticOperationsP getLogisticOperationsP() {
        return logisticOperationsP;
    }

    public void setLogisticOperationsP(LogisticOperationsP logisticOperationsP) {
        this.logisticOperationsP = logisticOperationsP;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public SimulationLogisticOperations(LogisticOperationsP logisticOperationsP, Simulation simulation){
        super();
        this.logisticOperationsP = logisticOperationsP;
        this.simulation = simulation;
    }
}
