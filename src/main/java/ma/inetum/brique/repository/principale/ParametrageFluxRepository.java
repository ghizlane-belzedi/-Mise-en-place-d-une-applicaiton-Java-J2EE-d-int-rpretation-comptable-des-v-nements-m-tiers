package ma.inetum.brique.repository.principale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.ParametrageFLux;

public interface ParametrageFluxRepository extends JpaRepository<ParametrageFLux, Long>, JpaSpecificationExecutor<ParametrageFLux>{
	@Query("select p from ParametrageFLux p where p.code = :code")
	public ParametrageFLux findByCode(@Param("code") String code);

}
