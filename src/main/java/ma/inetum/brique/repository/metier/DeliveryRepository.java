package ma.inetum.brique.repository.metier;

import ma.inetum.brique.model.metier.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>, JpaSpecificationExecutor<Delivery> {

    @Query(value = "SELECT d FROM Delivery d WHERE d.id >= :magic")
    public List<Delivery> findAllForBatch(@Param("magic") Long magic);

}