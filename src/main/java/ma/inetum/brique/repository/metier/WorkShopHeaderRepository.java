package ma.inetum.brique.repository.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.metier.WorkShopHeader;

public interface WorkShopHeaderRepository extends JpaRepository<WorkShopHeader, Long>, JpaSpecificationExecutor<WorkShopHeader>{
	
	
	
	@Query("select v from WorkShopHeader v where id >= :magic")
	public List<WorkShopHeader> findAllForBatch(Long magic);
	
	@Query(value = "select :fields from CS_ME_CDPOH h, CS_ME_CDPOL d where d.magic = h.magic", nativeQuery = true)
	public List<Object> findAll(@Param("fields") String fields);
	
}
