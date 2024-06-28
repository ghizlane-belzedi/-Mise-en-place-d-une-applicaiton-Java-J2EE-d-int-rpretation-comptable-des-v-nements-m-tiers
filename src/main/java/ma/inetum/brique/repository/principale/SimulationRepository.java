package ma.inetum.brique.repository.principale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationState;

public interface SimulationRepository extends JpaRepository<Simulation, Long>, JpaSpecificationExecutor<Simulation>{

	@Query("select s from Simulation s where s.flux.code = :code")
	public List<Simulation> findByFlux(@Param("code") String code);
	
	@Query("select count(1) from Simulation s where s.flux.code = :flux and s.state != :s1 and s.state != :s2")
	public Integer isNotEnabled(@Param("s1") SimulationState s1, @Param("s2") SimulationState s2, @Param("flux") String flux);
	
	@Query("select count(1) from Simulation s where s.flux.code = :fluxCode and s.state in (0, 2)")
	public Integer getNbrSimulationsInProgressByFluxCode(@Param("fluxCode") String fluxCode);
}
