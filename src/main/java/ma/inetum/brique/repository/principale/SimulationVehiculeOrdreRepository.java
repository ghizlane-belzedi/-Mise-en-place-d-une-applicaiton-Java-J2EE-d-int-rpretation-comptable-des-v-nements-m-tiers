package ma.inetum.brique.repository.principale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationVehiculeOrder;
import ma.inetum.brique.model.principale.VehiculeOrder;

public interface SimulationVehiculeOrdreRepository extends JpaRepository<SimulationVehiculeOrder, Long>, JpaSpecificationExecutor<SimulationVehiculeOrder>{


	@Query("select s.vehicule from SimulationVehiculeOrder s where s.simulation = :simulation")
	public List<VehiculeOrder> findBySimulation(@Param("simulation") Simulation simulation);
	
}
