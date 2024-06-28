package ma.inetum.brique.repository.principale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationWorkShop;
import ma.inetum.brique.model.principale.WorkShopHeader;

public interface SimulationWorkShopRepository extends JpaRepository<SimulationWorkShop, Long>, JpaSpecificationExecutor<SimulationWorkShop>{


	@Query("select s.header from SimulationWorkShop s where s.simulation = :simulation")
	public List<WorkShopHeader> findBySimulation(@Param("simulation") Simulation simulation);
	
}
