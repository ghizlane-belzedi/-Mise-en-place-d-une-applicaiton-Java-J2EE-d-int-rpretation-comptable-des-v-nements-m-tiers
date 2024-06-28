package ma.inetum.brique.repository.principale;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.WorkShopHeader;


public interface WorkShopHeaderPRepository extends JpaRepository<WorkShopHeader, Long>, JpaSpecificationExecutor<WorkShopHeader>{
	
	
	
	@Query("select v from WorkShopHeader v where v.creationDate <= :date and (sent = false or sent is null)")
	public List<WorkShopHeader> findAllBy(LocalDate date);
	
	@Query(value = "select :fields from CS_ME_CDPOH h, CS_ME_CDPOL d where d.magic = h.magic and (sent = false or sent is null)", nativeQuery = true)
	public List<Object> findAll(@Param("fields") String fields);
	
	
	
	
}
