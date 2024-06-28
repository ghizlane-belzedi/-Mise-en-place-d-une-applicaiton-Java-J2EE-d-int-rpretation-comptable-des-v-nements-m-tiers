package ma.inetum.brique.repository.principale;

import ma.inetum.brique.model.principale.DeliveryP;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SimulationDeliveryRepository extends JpaRepository<SimulationDelivery, Long>, JpaSpecificationExecutor<SimulationDelivery> {

    @Query("select s.deliveryP from SimulationDelivery s where s.simulation = :simulation")
    public List<DeliveryP> findBySimulation(@Param("simulation") Simulation simulation);
}
