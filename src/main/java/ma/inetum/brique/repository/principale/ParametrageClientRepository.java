package ma.inetum.brique.repository.principale;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.ParametrageClient;

public interface ParametrageClientRepository extends JpaRepository<ParametrageClient, Long>, JpaSpecificationExecutor<ParametrageClient> {

    @Query("select p from ParametrageClient p where p.codeMedtier = :codeMetier and p.site = :site ")
    public List<ParametrageClient> findByCodeMetierAndSite(@Param("codeMetier") String codeMetier, @Param("site") String site);

    @Query("select p from ParametrageClient p")
    public List<ParametrageClient> getAll();

}
