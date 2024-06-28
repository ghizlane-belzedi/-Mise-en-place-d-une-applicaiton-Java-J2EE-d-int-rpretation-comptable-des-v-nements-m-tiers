package ma.inetum.brique.repository.principale;

import ma.inetum.brique.model.principale.LogisticOperationsP;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationLogisticOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SimulationLogisticOperationsRepository extends JpaRepository<SimulationLogisticOperations, Long>, JpaSpecificationExecutor<SimulationLogisticOperations> {

    @Query("select s.logisticOperationsP from SimulationLogisticOperations s where s.simulation = :simulation")
    public List<LogisticOperationsP> findBySimulation(@Param("simulation") Simulation simulation);
}
