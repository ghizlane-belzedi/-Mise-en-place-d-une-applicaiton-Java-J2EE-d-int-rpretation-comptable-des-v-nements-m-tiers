package ma.inetum.brique.repository.coda;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.inetum.brique.model.coda.LinkQuantity;
import ma.inetum.brique.model.coda.LinkQuantityId;

public interface LinkQuantityRepository extends JpaRepository<LinkQuantity, LinkQuantityId> {

	
}
