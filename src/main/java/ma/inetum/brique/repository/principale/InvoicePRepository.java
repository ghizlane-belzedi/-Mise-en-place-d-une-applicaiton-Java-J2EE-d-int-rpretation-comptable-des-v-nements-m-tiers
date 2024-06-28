package ma.inetum.brique.repository.principale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.Invoice;



public interface InvoicePRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice>{
	
	
	
	@Query("select v from Invoice v where v.credate <= :date and (sent = false or sent is null) order by v.invoice, invdate, opertype desc")
	public List<Invoice> findAllBy(LocalDate date);
	
	@Query(value = "select :fields from CS_ME_CDINV v where (sent = false or sent is null)", nativeQuery = true)
	public List<Object> findAll(@Param("fields") String fields);
	
//	@Query("select v from Invoice v where sent = true and  ")
//	public List<Invoice> findAllFacturedNotLivred();
	
	
}
