package ma.inetum.brique.repository.principale;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.Parametrage;

public interface ParametrageRepository extends JpaRepository<Parametrage, Long>, JpaSpecificationExecutor<Parametrage>{


	@Query("SELECT p FROM Parametrage p WHERE p.categorie= :categorie")
	public Page<Parametrage> findallByCategorie(Pageable pageable, @Param("categorie") String categorie);
	@Query("select p from Parametrage p where p.codeMedtier = :code and p.categorie = :categorie")
	public Parametrage findByCode(@Param("code") String code, @Param("categorie") String categorie);
	
	@Query("select p from Parametrage p where p.categorie = :categorie")
	public List<Parametrage> findByCategorie(@Param("categorie") String categorie);

	@Query("SELECT DISTINCT p.categorie FROM Parametrage p")
	public List<String> getCategories();
}
