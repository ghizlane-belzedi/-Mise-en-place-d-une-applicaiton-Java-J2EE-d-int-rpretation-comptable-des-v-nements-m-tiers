package ma.inetum.brique.repository.principale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.Invoice;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationInvoice;

public interface SimulationInvoiceRepository extends JpaRepository<SimulationInvoice, Long>, JpaSpecificationExecutor<SimulationInvoice>{


	@Query("select s.invoice from SimulationInvoice s where s.simulation = :simulation")
	public List<Invoice> findBySimulation(@Param("simulation") Simulation simulation);
	
}
