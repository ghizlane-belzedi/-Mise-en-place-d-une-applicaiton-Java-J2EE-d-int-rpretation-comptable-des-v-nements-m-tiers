package ma.inetum.brique.repository.principale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ma.inetum.brique.model.principale.ParametrageFluxField;

public interface ParametrageFluxFieldRepository extends JpaRepository<ParametrageFluxField, Long>, JpaSpecificationExecutor<ParametrageFluxField>{


}
