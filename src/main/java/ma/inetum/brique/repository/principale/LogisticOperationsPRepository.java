package ma.inetum.brique.repository.principale;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ma.inetum.brique.model.principale.LogisticOperationsP;

public interface LogisticOperationsPRepository extends JpaRepository<LogisticOperationsP, Long>, JpaSpecificationExecutor<LogisticOperationsP> {

    //@Query(value = "SELECT l FROM LogisticOperationsP l WHERE l.dateChargement <= :date and l.opertype in('S', 'T')  and (l.sent = false or l.sent is null) ")
	@Query(value = "SELECT l FROM LogisticOperationsP l WHERE l.credate <= :date and l.opertype = 'S' and (l.sent = false or l.sent is null) ")
    public List<LogisticOperationsP> findAllByDate(LocalDate date);

}
