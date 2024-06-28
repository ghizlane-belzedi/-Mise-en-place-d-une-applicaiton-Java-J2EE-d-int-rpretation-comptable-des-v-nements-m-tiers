package ma.inetum.brique.repository.principale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.ParametrageFLux;


public interface ChargementRepository extends JpaRepository<Chargement, Long>, JpaSpecificationExecutor<Chargement>{
		
	@Query(name="SELECT MAX(c.id) FROM Chargement c WHERE c.flux.id = :flux.id and c.state = :state")
	public Page<Chargement> findLastChargementIdByFluxAndState(ParametrageFLux flux, ChargementState state, Pageable page);
	
	@Query(name="SELECT c FROM Chargement c WHERE c.flux.id = :flux.id and c.last is not null = ")
	public Page<Chargement> findLastChargementByFlux(ParametrageFLux flux, Pageable page);
	
	@Query("select max(c.id) from Chargement c WHERE c.flux.code = :flux")
	public Long findLastId(String flux);
//	@Query(name="SELECT c FROM Chargement c WHERE id = (select max(c1.id) from Chargement c1 WHERE c1.flux.code = c.flux.code) ")
//	public List<Chargement> getLastChargement();
//	
//	public default List<Chargement> findAllForDatatable(String flux, ChargementState statut, LocalDateTime dateDebut, LocalDateTime dateFin, int page, int size, String sort, String dir){
//		List<Chargement> chargements = new ArrayList<>();
//		Pageable pageable = PageRequest.of(page, size, Direction.fromOptionalString(dir).orElse(Direction.DESC), sort);
//		Specification<Chargement> specification = (root, query, criteriaBuilder) -> {
//			List<Predicate> predicates = new ArrayList<>();
//			if (flux != null) {	
//				predicates.add(criteriaBuilder.like(root.get("flux.code"), flux));
//			}
//			if (statut != null) {
//				predicates.add(criteriaBuilder.equal(root.get("state"), statut));
//			}
//			if (dateDebut != null) {
//				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateChargement"), dateDebut));
//			}
//			if (dateFin != null) {
//				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateChargement"), dateFin));
//			}
//			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//
//		};
//		
//		return chargements;
//	}
	
}
