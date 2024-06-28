package ma.inetum.brique.repository.principale;


import ma.inetum.brique.model.principale.ParametrageSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParametrageSupplierRepository extends JpaRepository<ParametrageSupplier, Long>, JpaSpecificationExecutor<ParametrageSupplier> {

    @Query("select ps from ParametrageSupplier ps where ps.codeMedtier = :codeMetier and ps.currency = :currency")
    public ParametrageSupplier findByCodeMetierAndCurrency(@Param("codeMetier") String codeMetier, @Param("currency") String currency);

    @Query("select p from ParametrageSupplier p")
    public List<ParametrageSupplier> getAll();

}
