package ma.inetum.brique.repository.principale;

import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationVehicleFinalisation;
import ma.inetum.brique.model.principale.VehicleFinalisationHeaderP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SimulationVehicleFinalisationRepository extends JpaRepository<SimulationVehicleFinalisation, Long>, JpaSpecificationExecutor<SimulationVehicleFinalisation> {

    @Query("select s.vehicleFinalisationHeaderP from SimulationVehicleFinalisation s where s.simulation = :simulation")
    public List<VehicleFinalisationHeaderP> findBySimulation(@Param("simulation") Simulation simulation);


}
