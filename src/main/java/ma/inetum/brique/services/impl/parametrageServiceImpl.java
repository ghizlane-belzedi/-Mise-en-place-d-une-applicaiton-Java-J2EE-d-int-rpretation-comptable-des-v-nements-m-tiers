package ma.inetum.brique.services.impl;


import ma.inetum.brique.bean.ParamView;
import ma.inetum.brique.mapper.ParamMapper;
import ma.inetum.brique.model.principale.Parametrage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import javax.persistence.criteria.Predicate;
import ma.inetum.brique.repository.principale.ParametrageRepository;
import ma.inetum.brique.services.ParametrageService;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class parametrageServiceImpl implements ParametrageService {
    @Autowired
    ParametrageRepository parametrageRepository;
    private static ParamMapper mapper = ParamMapper.getInstance();

    private static final Logger log = LoggerFactory.getLogger(parametrageServiceImpl.class);

    @Override
    public DataTablesOutput<ParamView> findallByCategorie(DataTablesInput input, String categorie){
        DataTablesOutput<ParamView> view = new DataTablesOutput<>();
        String[] search = input.getSearch().getValue().split(";");
        Pageable pageable = PageRequest.of(input.getStart() / input.getLength(), input.getLength(), Direction.fromOptionalString(input.getOrder().get(0).getDir()).orElse(Direction.DESC), input.getColumns().get(input.getOrder().get(0).getColumn() ).getName());
        Specification<Parametrage> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (search.length > 0 && search[0] != null && !search[0].isBlank()) {
                predicates.add(criteriaBuilder.like(root.get("codeMedtier"), search[0]));
            }
            try {
                if(search.length > 1 && search[1] != null && !search[1].isBlank())
                    predicates.add(criteriaBuilder.like(root.get("codeFinance"), search[1]));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            try {
                    predicates.add(criteriaBuilder.like(root.get("categorie"), categorie));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        //Page<Parametrage> page = parametrageRepository.findallByCategorie(pageable, categorie);
        Page<Parametrage> page = parametrageRepository.findAll(specification, pageable);

        view.setData(page.stream().map(e -> mapper.paramToParamView(e)).collect(Collectors.toList()));
        view.setDraw(input.getDraw());
        view.setError(null);
        view.setRecordsFiltered(page.getTotalElements());
        view.setRecordsTotal(parametrageRepository.count());

        return view;
    }
    @Override
    public List<ParamView> findallByCategorie(String categorie){
        List<Parametrage> param = parametrageRepository.findByCategorie(categorie);

        return param.stream().map(p -> mapper.paramToParamView(p)).collect(Collectors.toList());
    }
    @Override
    public List<String> getCategories(){
        List<String> categories = parametrageRepository.getCategories();
        return categories;
    }

    @Override
    public List<ParamView> getAll() {
        List<Parametrage> param = parametrageRepository.findAll(Sort.by(Sort.Direction.ASC, "categorie"));
        return param.stream().map(p -> mapper.paramToParamView(p)).collect(Collectors.toList());
    }

    @Override
    public DataTablesOutput<ParamView> getAll(DataTablesInput input) {
        DataTablesOutput<ParamView> view = new DataTablesOutput<>();
        String[] search = input.getSearch().getValue().split(";");
        Pageable pageable = PageRequest.of(input.getStart() / input.getLength(), input.getLength(), Direction.fromOptionalString(input.getOrder().get(0).getDir()).orElse(Direction.DESC), input.getColumns().get(input.getOrder().get(0).getColumn() ).getName());
        Specification<Parametrage> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (search.length > 0 && search[0] != null && !search[0].isBlank()) {
                predicates.add(criteriaBuilder.like(root.get("categorie"), search[0]));
            }
            try {
                if(search.length > 1 && search[1] != null && !search[1].isBlank())
                    predicates.add(criteriaBuilder.like(root.get("codeMedtier"), search[1]));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            try {
                if(search.length > 2 && search[2] != null && !search[2].isBlank())
                    predicates.add(criteriaBuilder.like(root.get("codeFinance"), search[2]));
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        };
        Page<Parametrage> page = parametrageRepository.findAll(specification, pageable);

        view.setData(page.stream().map(e -> mapper.paramToParamView(e)).collect(Collectors.toList()));
        view.setDraw(input.getDraw());
        view.setError(null);
        view.setRecordsFiltered(page.getTotalElements());
        view.setRecordsTotal(parametrageRepository.count());
        return view;
    }

    @Override
    public void add(ParamView p, String categorie){
        Parametrage parametrage = new Parametrage();
        parametrage.setCategorie(categorie);
        parametrage.setCodeFinance(p.getCodeFinance());
        parametrage.setCodeMedtier(p.getCodeMedtier());
        parametrage.setDescription1(p.getDescription1());
        parametrage.setAddtionalField(p.getAddtionalField());
        parametrage.setAddtionalFieldDescr(p.getAddtionalFieldDescr());
        parametrageRepository.save(parametrage);
    }

    @Override
    public ParamView findById(Long id) {
        if (id == null || id == 0)
            throw new RuntimeException("Identifiant absent");
        Parametrage parametrage = parametrageRepository.findById(id).orElse(null);
        ParamView p = null;
        if (parametrage != null) {
            p = mapper.paramToParamView(parametrage);
        }
        else {
            throw new RuntimeException("Aucun paramètre trouvé");
        }
        return p;
    }
    @Override
    @Transactional
    public void edit(ParamView view){
        if (view.getParamId() == null || view.getParamId() == 0) {
            throw new RuntimeException("Identifiant absent");
        }
        Parametrage parametrage = parametrageRepository.findById(view.getParamId()).orElse(null);
        if (parametrage != null) {
//            parametrage.setCategorie(view.getCategorie());
            parametrage.setCodeFinance(view.getCodeFinance());
            parametrage.setCodeMedtier(view.getCodeMedtier());
            parametrage.setDescription1(view.getDescription1());
            parametrage.setAddtionalField(view.getAddtionalField());
            parametrage.setAddtionalFieldDescr(view.getAddtionalFieldDescr());

            parametrageRepository.save(parametrage);
        } else {
            throw new RuntimeException("Aucun Client trouvé");
        }
    }
}
