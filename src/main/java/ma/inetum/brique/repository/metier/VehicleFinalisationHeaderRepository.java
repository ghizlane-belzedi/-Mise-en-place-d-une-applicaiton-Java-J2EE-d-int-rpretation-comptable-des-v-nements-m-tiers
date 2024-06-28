package ma.inetum.brique.repository.metier;

import ma.inetum.brique.model.metier.VehicleFinalisationDetails;
import ma.inetum.brique.model.metier.VehicleFinalisationHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleFinalisationHeaderRepository extends JpaRepository<VehicleFinalisationHeader, Long>, JpaSpecificationExecutor<VehicleFinalisationHeader> {

    @Query(value = "SELECT v FROM VehicleFinalisationHeader v WHERE v.id >= :magic")
    public List<VehicleFinalisationHeader> findAllForBatch(@Param("magic") Long magic);

    @Query(value = "SELECT v FROM VehicleFinalisationDetails v WHERE v.vehicleFinalisationHeader.id = :magic ")
    public List<VehicleFinalisationDetails> findDetails(Long magic);
}
