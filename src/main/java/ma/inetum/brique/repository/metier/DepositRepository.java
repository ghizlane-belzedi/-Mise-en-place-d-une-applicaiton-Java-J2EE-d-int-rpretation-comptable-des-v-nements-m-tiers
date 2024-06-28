package ma.inetum.brique.repository.metier;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepositRepository {
	
	
	
//	@Query(value = "select * from V_CS_ME_CDDEP v where creationDate = CAST(GETDATE() - 1 AS DATE)", nativeQuery = true)
//	public List<Deposit> findAllForBatch(Long magic);
	
	@Query(value = "select :fields from CS_ME_CDDEP v ", nativeQuery = true)
	public List<Object> findAll(@Param("fields") String fields);
	
}
