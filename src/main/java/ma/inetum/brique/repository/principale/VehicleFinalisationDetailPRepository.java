package ma.inetum.brique.repository.principale;

import ma.inetum.brique.model.principale.VehicleFinalisationDetailsP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VehicleFinalisationDetailPRepository extends JpaRepository<VehicleFinalisationDetailsP, Long>, JpaSpecificationExecutor<VehicleFinalisationDetailsP> {
}
