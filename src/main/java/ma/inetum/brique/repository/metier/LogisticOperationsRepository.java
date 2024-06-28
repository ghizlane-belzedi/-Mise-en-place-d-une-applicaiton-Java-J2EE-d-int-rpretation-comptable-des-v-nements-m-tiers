package ma.inetum.brique.repository.metier;

import ma.inetum.brique.model.metier.LogisticOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogisticOperationsRepository extends JpaRepository<LogisticOperations, Long>, JpaSpecificationExecutor<LogisticOperations> {

    @Query(value = "SELECT l FROM LogisticOperations l WHERE l.id >= :magic")
    public List<LogisticOperations> findAllForBatch(@Param("magic") Long magic);

}
