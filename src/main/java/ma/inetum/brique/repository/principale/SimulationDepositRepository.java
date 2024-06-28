package ma.inetum.brique.repository.principale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.Deposit;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationDeposit;

public interface SimulationDepositRepository extends JpaRepository<SimulationDeposit, Long>, JpaSpecificationExecutor<SimulationDeposit>{


	@Query("select s.deposit from SimulationDeposit s where s.simulation = :simulation")
	public List<Deposit> findBySimulation(@Param("simulation") Simulation simulation);
	
}
