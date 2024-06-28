package ma.inetum.brique.repository.metier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.metier.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice>{
	
	
	
	@Query("select v from Invoice v where id >= :magic")
	public List<Invoice> findAllForBatch(Long magic);
	
	@Query(value = "select :fields from CS_ME_CDINV v ", nativeQuery = true)
	public List<Object> findAll(@Param("fields") String fields);
	
}
