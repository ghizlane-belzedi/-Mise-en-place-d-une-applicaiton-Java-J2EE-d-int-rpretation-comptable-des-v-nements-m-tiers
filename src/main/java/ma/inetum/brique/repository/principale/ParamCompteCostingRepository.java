package ma.inetum.brique.repository.principale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.inetum.brique.model.principale.ParamCompteCosting;

public interface ParamCompteCostingRepository extends JpaRepository<ParamCompteCosting, Long>, JpaSpecificationExecutor<ParamCompteCosting>{

	@Query("select s from ParamCompteCosting s where s.flux.code = :flux and s.codeAnalyse = TRIM(:code) and s.codeFournisseur = TRIM(:supplier)")
	public List<ParamCompteCosting> findByFluxAndSupplierAndCode(@Param("flux") String flux, @Param("code") String code, @Param("supplier") String supplier);
	
	@Query("select s from ParamCompteCosting s where s.flux.code = :flux")
	public List<ParamCompteCosting> findByFlux(@Param("flux") String flux);
	
	@Query("select s from ParamCompteCosting s where s.flux.code = :flux and s.codeAnalyse = TRIM(:analysisCode)")
	public List<ParamCompteCosting> findByFluxAndAnalysisCode(String flux, String analysisCode);
}
