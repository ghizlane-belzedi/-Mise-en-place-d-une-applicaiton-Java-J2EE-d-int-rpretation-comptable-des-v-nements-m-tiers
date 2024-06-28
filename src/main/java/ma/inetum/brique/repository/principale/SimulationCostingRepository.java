package ma.inetum.brique.repository.principale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.CostingHeader;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationCosting;

public interface SimulationCostingRepository extends JpaRepository<SimulationCosting, Long>, JpaSpecificationExecutor<SimulationCosting>{


	@Query("select s.header from SimulationCosting s where s.simulation = :simulation")
	public List<CostingHeader> findBySimulation(@Param("simulation") Simulation simulation);
	
}
