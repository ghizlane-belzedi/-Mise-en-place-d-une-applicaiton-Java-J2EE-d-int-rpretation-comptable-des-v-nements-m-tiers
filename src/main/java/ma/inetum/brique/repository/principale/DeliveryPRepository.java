package ma.inetum.brique.repository.principale;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ma.inetum.brique.model.principale.DeliveryP;


public interface DeliveryPRepository extends JpaRepository<DeliveryP, Long>, JpaSpecificationExecutor<DeliveryP> {

    @Query(value = "SELECT d FROM DeliveryP d WHERE d.creDate <= :date and (d.sent = false or d.sent is null) ")
    public List<DeliveryP> findAllByDate(LocalDate date);
}
