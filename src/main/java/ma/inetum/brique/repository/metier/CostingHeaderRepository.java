package ma.inetum.brique.repository.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.metier.CostingHeader;

public interface CostingHeaderRepository extends JpaRepository<CostingHeader, Long>, JpaSpecificationExecutor<CostingHeader>{
	
	
	
	@Query("select v from CostingHeader v where id >= :magic")
	public List<CostingHeader> findAllForBatch(Long magic);
	
	@Query(value = "select :fields from CS_ME_CDCOH h, CS_ME_CDCOL d where d.magic = h.magic", nativeQuery = true)
	public List<Object> findAll(@Param("fields") String fields);
	
}
