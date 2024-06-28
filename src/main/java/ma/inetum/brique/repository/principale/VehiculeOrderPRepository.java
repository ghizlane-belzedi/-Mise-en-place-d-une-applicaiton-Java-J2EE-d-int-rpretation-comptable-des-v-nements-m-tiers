package ma.inetum.brique.repository.principale;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.VehiculeOrder;


public interface VehiculeOrderPRepository extends JpaRepository<VehiculeOrder, Long>, JpaSpecificationExecutor<VehiculeOrder>{
	
	
	
	@Query("select v from VehiculeOrder v where v.credate <= :date and (sent = false or sent is null)")
	public List<VehiculeOrder> findAllBy(LocalDate date);
	
	@Query(value = "select :fields from CS_ME_CDORD v where (sent = false or sent is null)", nativeQuery = true)
	public List<Object> findAll(@Param("fields") String fields);
	
	
	
	
}
