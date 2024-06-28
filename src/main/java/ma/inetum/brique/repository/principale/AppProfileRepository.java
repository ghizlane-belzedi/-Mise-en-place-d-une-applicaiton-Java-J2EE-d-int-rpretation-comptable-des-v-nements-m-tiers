package ma.inetum.brique.repository.principale;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ma.inetum.brique.model.principale.AppProfile;

public interface AppProfileRepository extends JpaRepository<AppProfile, Long>, JpaSpecificationExecutor<AppProfile>{

	public List<AppProfile> findAll();
	public List<AppProfile> findByCode(String code);
	
	public default Set<AppProfile> searchByCriteria(String code, String descrption, String role, int page, int size, String sort, String dir) {
		Pageable pageable = PageRequest.of(page, size, Direction.fromOptionalString(dir).orElse(Direction.DESC), sort);
		Specification<AppProfile> specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (code != null) {
				predicates.add(criteriaBuilder.like(root.get("code"), code));
			}
			if (descrption != null) {
				predicates.add(criteriaBuilder.like(root.get("nom"), descrption));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};

		return this.findAll(specification, pageable).toSet();
	}

}
