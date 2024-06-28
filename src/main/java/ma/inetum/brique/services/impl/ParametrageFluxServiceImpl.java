package ma.inetum.brique.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ma.inetum.brique.bean.FluxEditView;
import ma.inetum.brique.bean.FluxView;
import ma.inetum.brique.mapper.FluxMapper;
import ma.inetum.brique.model.principale.ParametrageFLux;
import ma.inetum.brique.model.principale.ParametrageFluxField;
import ma.inetum.brique.repository.principale.ParametrageFluxFieldRepository;
import ma.inetum.brique.repository.principale.ParametrageFluxRepository;
import ma.inetum.brique.services.ParametrageFluxService;

@Service
public class ParametrageFluxServiceImpl implements ParametrageFluxService {

	@Autowired
	private ParametrageFluxRepository fluxRepository;
	@Autowired
	private ParametrageFluxFieldRepository fluxFieldRepository;
	private FluxMapper mapper = FluxMapper.getInstance();
	@Override
	public List<FluxView> findByCriteria(String nom, String code, int page, int size, String sort, String dir) {
		Pageable pageable = PageRequest.of(page, size, Direction.fromOptionalString(dir).orElse(Direction.DESC), sort);
		Specification<ParametrageFLux> specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (nom != null) {
				predicates.add(criteriaBuilder.like(root.get("nom"), nom));
			}
			if (code != null) {
				predicates.add(criteriaBuilder.like(root.get("code"), code));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};

		Set<ParametrageFLux> flux = fluxRepository.findAll(specification, pageable).toSet();
		return flux.stream().map(f -> mapper.fluxToFluxView(f)).collect(Collectors.toList());
	}
	@Override
	public List<FluxView> getAll() {
		List<ParametrageFLux> flux = fluxRepository.findAll(Sort.by(Direction.ASC, "nom"));
		return flux.stream().map(f -> mapper.fluxToFluxView(f)).collect(Collectors.toList());
	}
	@Override
	public FluxEditView findById(Long id) {
		if (id == null || id == 0)
			throw new RuntimeException("Identifiant absent");
		ParametrageFLux flux = fluxRepository.findById(id).orElse(null);
		FluxEditView c = null;
		if (flux != null) {
			c = mapper.fluxToFluxEditView(flux);
		} else {
			throw new RuntimeException("Aucun Flux trouvé");
		}
		return c;
	}
	@Override
	@Transactional
	public void edit(FluxEditView view) {
		if (view.getFluxId() == null || view.getFluxId() == 0) {
			throw new RuntimeException("Identifiant absent");
		}
		ParametrageFLux flux = fluxRepository.findById(view.getFluxId()).orElse(null);
		if (flux != null) {
			flux.setNom(view.getNom());
			for(FluxEditView.FluxField f :  view.getFields()) {
				for(ParametrageFluxField field : flux.getFields()) {
					if(field.getId().equals(f.getId())) {
						if(!f.isVisible()) {
							field.setVisible(false);
							field.setOrdre(0);
						} else {
							field.setVisible(true);
							field.setOrdre(f.getOrdre());
						}
						field.setDescription(f.getDescription());
						fluxFieldRepository.save(field);
					}
				}
				//				ParametrageFluxField field = flux.getFields().stream().filter(e -> e.getId() == f.getId()).findAny().orElse(null);
//				if(field == null ) {
//					continue;
//				}

			}
			fluxRepository.save(flux);
		} else {
			throw new RuntimeException("Aucun Flux trouvé");
		}
	}

	public ParametrageFLux findFluxByCode (String fluxCode) {
		return fluxRepository.findByCode(fluxCode);
	}

}
