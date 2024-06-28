package ma.inetum.brique.repository.principale;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ma.inetum.brique.model.principale.VehicleFinalisationHeaderP;

public interface VehicleFinalisationHeaderPRepository extends JpaRepository<VehicleFinalisationHeaderP, Long>, JpaSpecificationExecutor<VehicleFinalisationHeaderP> {
    @Query(value = "SELECT v FROM VehicleFinalisationHeaderP v WHERE v.credate <= :date and (v.sent = false or v.sent is null) ")
    public List<VehicleFinalisationHeaderP> findAllByDate(LocalDate date);

}
