package ma.inetum.brique.repository.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.metier.VehiculeOrder;

public interface VehiculeOrderRepository extends JpaRepository<VehiculeOrder, Long>, JpaSpecificationExecutor<VehiculeOrder>{
	
	
	
	@Query("select v from VehiculeOrder v where id >= :magic")
	public List<VehiculeOrder> findAllForBatch(Long magic);
	
	@Query(value = "select :fields from CS_ME_CDORD v ", nativeQuery = true)
	public List<Object> findAll(@Param("fields") String fields);
	
}
