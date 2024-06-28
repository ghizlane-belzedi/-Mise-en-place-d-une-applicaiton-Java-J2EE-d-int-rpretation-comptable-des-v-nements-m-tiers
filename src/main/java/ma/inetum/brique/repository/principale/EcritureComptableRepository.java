package ma.inetum.brique.repository.principale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ma.inetum.brique.model.principale.EcritureComptable;

public interface EcritureComptableRepository extends JpaRepository<EcritureComptable, Long>, JpaSpecificationExecutor<EcritureComptable>{

	
}
