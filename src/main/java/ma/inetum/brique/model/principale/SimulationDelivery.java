package ma.inetum.brique.model.principale;

import javax.persistence.*;

@Entity
@Table(name = "SIMULATION_CDDEL")
public class SimulationDelivery {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DELIVERY_ID")
    private DeliveryP deliveryP;

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID")
    private Simulation simulation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryP getDeliveryP() {
        return deliveryP;
    }

    public void setDeliveryP(DeliveryP deliveryP) {
        this.deliveryP = deliveryP;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public SimulationDelivery(DeliveryP deliveryP, Simulation simulation){
        super();
        this.deliveryP = deliveryP;
        this.simulation = simulation;
    }
}
