package ma.inetum.brique.repository.coda;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.inetum.brique.model.coda.LinkLine;
import ma.inetum.brique.model.coda.LinkLineId;

public interface LinkLineRepository extends JpaRepository<LinkLine, LinkLineId> {

	
}
