package ma.inetum.brique.repository.principale;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.CostingHeader;


public interface CostingHeaderPRepository extends JpaRepository<CostingHeader, Long>, JpaSpecificationExecutor<CostingHeader>{
	
	
	
	@Query("select v from CostingHeader v where v.creationDate <= :date and (sent = false or sent is null)")
	public List<CostingHeader> findAllBy(LocalDate date);
	
	@Query(value = "select :fields from CS_ME_CDCOH h, CS_ME_CDCOL d where d.magic = h.magic and (sent = false or sent is null)", nativeQuery = true)
	public List<Object> findAll(@Param("fields") String fields);
	
	
	
	
}
