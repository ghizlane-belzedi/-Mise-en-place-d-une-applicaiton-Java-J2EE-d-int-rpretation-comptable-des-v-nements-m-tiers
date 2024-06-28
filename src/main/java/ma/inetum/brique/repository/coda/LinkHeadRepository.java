package ma.inetum.brique.repository.coda;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.inetum.brique.model.coda.LinkHead;
import ma.inetum.brique.model.coda.LinkHeadId;


public interface LinkHeadRepository extends JpaRepository<LinkHead, LinkHeadId> {
	
}