package ma.inetum.brique.repository.metier;

import ma.inetum.brique.model.metier.VehicleFinalisationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VehicleFinalisationDetailRepository extends JpaRepository<VehicleFinalisationDetails, Long>, JpaSpecificationExecutor<VehicleFinalisationDetails> {

}
