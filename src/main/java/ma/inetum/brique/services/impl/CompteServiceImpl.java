package ma.inetum.brique.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.apache.commons.collections.FunctorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ma.inetum.brique.bean.CompteView;
import ma.inetum.brique.bean.SchemaEditView;
import ma.inetum.brique.mapper.CompteCostingMapper;
import ma.inetum.brique.model.principale.ParamCompteCosting;
import ma.inetum.brique.repository.principale.ParamCompteCostingRepository;
import ma.inetum.brique.repository.principale.ParametrageFluxRepository;
import ma.inetum.brique.services.CompteService;
@Service
public class CompteServiceImpl implements CompteService{

	private static final Logger log = LoggerFactory.getLogger(CompteServiceImpl.class);
	CompteCostingMapper mapper = CompteCostingMapper.getInstance();
	
	@Autowired 
	ParamCompteCostingRepository paramCompteCostingRepository;
	@Autowired
	ParametrageFluxRepository fluxRepository;
	@Override
	public DataTablesOutput<CompteView> getAll(DataTablesInput input) {
		DataTablesOutput<CompteView> view = new DataTablesOutput<>();
		String[] search = input.getSearch().getValue().split(";");
		Pageable pageable = null;
		if(input.getLength() > 0 ) {
			pageable = PageRequest.of(input.getStart() / input.getLength(), input.getLength(), Direction.fromOptionalString(input.getOrder().get(0).getDir()).orElse(Direction.DESC), input.getColumns().get(input.getOrder().get(0).getColumn() ).getName());
		} else {
			pageable = PageRequest.of(0, Integer.MAX_VALUE, Direction.fromOptionalString(input.getOrder().get(0).getDir()).orElse(Direction.DESC), input.getColumns().get(input.getOrder().get(0).getColumn() ).getName());
		}
		Specification<ParamCompteCosting> specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (search.length > 0 && search[0] != null && !search[0].isBlank()) {	
				predicates.add(criteriaBuilder.like(root.join("schemaComptable").join("flux").get("code"), search[0]));
			}
			try {
				if(search.length > 1 && search[1] != null && !search[1].isBlank())
					predicates.add(criteriaBuilder.equal(root.get("sens"), search[1].charAt(0)));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			try {
				if(search.length > 2 && search[2] != null && !search[2].isBlank())
					predicates.add(criteriaBuilder.like(root.join("schemaComptable").get("codeFournisseur"), "%" + search[2] + "%"));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			try {
				if(search.length > 3 && search[3] != null && !search[3].isBlank())
					predicates.add(criteriaBuilder.like(root.join("schemaComptable").get("costCenter"), "%" + search[3] + "%"));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			try {
				if(search.length > 4 && search[4] != null && !search[4].isBlank())
					predicates.add(criteriaBuilder.like(root.get("accountNumber"), "%" + search[4] + "%"));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			try {
				if(search.length > 5 && search[5] != null && !search[5].isBlank())
					predicates.add(criteriaBuilder.like(root.get("accountDescription"), "%" + search[5] +"%"));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};
		
		Page<ParamCompteCosting> comptes = paramCompteCostingRepository.findAll(specification, pageable);
		
		view.setData(comptes.stream().map(e -> mapper.entityTobean(e)).collect(Collectors.toList()));
		view.setDraw(input.getDraw());
		view.setError(null);
		view.setRecordsFiltered(comptes.getTotalElements());
		view.setRecordsTotal(paramCompteCostingRepository.count());
		return view;
	}
	@Override
	public void add(SchemaEditView s) {
		ParamCompteCosting paramCompteCosting = new ParamCompteCosting();
		paramCompteCosting.setCode(s.getCode());
		paramCompteCosting.setCodeAnalyse(s.getCodeAnalyse());
		paramCompteCosting.setCodeFournisseur(s.getFournisseur());
		paramCompteCosting.setCostCenter(s.getCostCenter());
		paramCompteCosting.setDescription(s.getDescription());
		paramCompteCosting.setFlux(fluxRepository.findByCode(s.getFlux()));
		if(s.getCompteDebiteurActif()) {
			paramCompteCosting.setCompteDebiteur(s.getCompteDebiteur());
			paramCompteCosting.setCompteCrediteurDescription(s.getCompteDebiteurDescription());
		}
		if(s.getCompteTvaActif()) {
			paramCompteCosting.setCompteTVA(s.getCompteTva());
			paramCompteCosting.setTvaAccountDescription(s.getCompteTvaDescription());
		}
		if(s.getCompteCrediteurActif()) {
			paramCompteCosting.setCompteCrediteur(s.getCompteCrediteur());
			paramCompteCosting.setCompteCrediteurDescription(s.getCompteCrediteurDescription());
		}
		if ((s.getId() != null) && (s.getId()> 0L)) {
			paramCompteCosting.setId(s.getId());
		}
		paramCompteCostingRepository.save(paramCompteCosting);
	}
	@Override
	public void edit(SchemaEditView s) {
		ParamCompteCosting schemaComptable = paramCompteCostingRepository.getOne(s.getId());
		schemaComptable.setCodeAnalyse(s.getCodeAnalyse());
		schemaComptable.setCodeFournisseur(s.getFournisseur());
		schemaComptable.setCostCenter(s.getCostCenter());
		schemaComptable.setDescription(s.getDescription());
		schemaComptable.setFlux(fluxRepository.findByCode(s.getFlux()));
		paramCompteCostingRepository.save(schemaComptable);
	}
	@Override
	public SchemaEditView findById(Long id) {
		Optional<ParamCompteCosting> schema_ = paramCompteCostingRepository.findById(id);
		if(schema_.isEmpty()) {
			throw new FunctorException("Aucune schema trouvé");
		} else {
			SchemaEditView schema = new SchemaEditView();
			ParamCompteCosting s = schema_.get();
			schema.setCode(s.getCode());
			schema.setCodeAnalyse(s.getCodeAnalyse());
			schema.setFournisseur(s.getCodeFournisseur());
			schema.setCostCenter(s.getCostCenter());
			schema.setDescription(s.getDescription());
			schema.setFlux(s.getFlux().getCode());
			schema.setId(s.getId());
			schema.setCompteDebiteurActif(false);
			schema.setCompteTvaActif(false);
			schema.setCompteCrediteurActif(false);
			if (s.getCompteDebiteur() != null) {
				schema.setCompteDebiteur(s.getCompteDebiteur());
				schema.setCompteDebiteurDescription(s.getCompteDebiteurDescription());
				schema.setCompteDebiteurActif(s.getCompteDebiteur() != null);
			}
			if (s.getCompteCrediteur() != null) {
				schema.setCompteCrediteur(s.getCompteCrediteur());
				schema.setCompteCrediteurDescription(s.getCompteCrediteurDescription());
				schema.setCompteCrediteurActif(s.getCompteCrediteur() != null);
			}
			if (s.getCompteTVA() != null) {
				schema.setCompteTva(s.getCompteTVA());
				schema.setCompteTvaDescription(s.getTvaAccountDescription());
				schema.setCompteTvaActif(s.getCompteTVA() != null);
			}
			
			return schema;
		}
	}

}
