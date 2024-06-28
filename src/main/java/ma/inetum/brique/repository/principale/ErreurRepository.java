package ma.inetum.brique.repository.principale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ma.inetum.brique.model.principale.Erreur;

public interface ErreurRepository  extends JpaRepository<Erreur, Long>, JpaSpecificationExecutor<Erreur>{

}
