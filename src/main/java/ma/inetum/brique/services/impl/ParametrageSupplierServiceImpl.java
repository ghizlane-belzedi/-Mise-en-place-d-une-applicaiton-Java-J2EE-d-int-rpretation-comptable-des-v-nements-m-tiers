package ma.inetum.brique.services.impl;

import ma.inetum.brique.bean.ParamSupplierView;
import ma.inetum.brique.mapper.ParamSupplierMapper;
import ma.inetum.brique.model.principale.ParametrageSupplier;
import ma.inetum.brique.repository.principale.ParametrageSupplierRepository;
import ma.inetum.brique.services.ParametrageSupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParametrageSupplierServiceImpl implements ParametrageSupplierService {

    @Autowired
    ParametrageSupplierRepository parametrageSupplierRepository;

    private static ParamSupplierMapper mapper = ParamSupplierMapper.getInstance();
    private static final Logger log = LoggerFactory.getLogger(ParametrageSupplierServiceImpl.class);

    @Override
    public List<ParamSupplierView> getAll(){
        List<ParametrageSupplier> param = parametrageSupplierRepository.findAll(Sort.by(Sort.Direction.ASC, "type"));
        return param.stream().map(p -> mapper.paramSupplierToView(p)).collect(Collectors.toList());
    }

    @Override
    public DataTablesOutput<ParamSupplierView> getAll(DataTablesInput input) {
        DataTablesOutput<ParamSupplierView> view = new DataTablesOutput<>();
        String[] search = input.getSearch().getValue().split(";");
        Pageable pageable = PageRequest.of(input.getStart() / input.getLength(), input.getLength(), Sort.Direction.fromOptionalString(input.getOrder().get(0).getDir()).orElse(Sort.Direction.DESC), input.getColumns().get(input.getOrder().get(0).getColumn() ).getName());
        Specification<ParametrageSupplier> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (search.length > 0 && search[0] != null && !search[0].isBlank()) {
                predicates.add(criteriaBuilder.like(root.get("type"), search[0]));
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
        Page<ParametrageSupplier> page = parametrageSupplierRepository.findAll(specification, pageable);

        view.setData(page.stream().map(e -> mapper.paramSupplierToView(e)).collect(Collectors.toList()));
        view.setDraw(input.getDraw());
        view.setError(null);
        view.setRecordsFiltered(page.getTotalElements());
        view.setRecordsTotal(parametrageSupplierRepository.count());
        return view;
    }

    @Override
    public void add(ParamSupplierView p){
        ParametrageSupplier parametrageSupplier = new ParametrageSupplier();
        parametrageSupplier.setCodeMedtier(p.getCodeMedtier());
        parametrageSupplier.setCodeFinance(p.getCodeFinance());
        parametrageSupplier.setType(p.getType());
        parametrageSupplier.setDescription1(p.getDescription1());
        parametrageSupplier.setCurrency(p.getCurrency());
        parametrageSupplierRepository.save(parametrageSupplier);
    }

    @Override
    public ParamSupplierView findById(Long id){
        if (id == null || id == 0)
            throw new RuntimeException("Identifiant absent");
        ParametrageSupplier parametrageSupplier = parametrageSupplierRepository.findById(id).orElse(null);
        ParamSupplierView p = null;
        if (parametrageSupplier != null) {
            p = mapper.paramSupplierToView(parametrageSupplier);
        }
        else {
            throw new RuntimeException("Aucun paramètre trouvé");
        }
        return p;
    }

    @Override
    public void edit(ParamSupplierView view){
        if (view.getId() == null || view.getId() == 0) {
            throw new RuntimeException("Identifiant absent");
        }
        ParametrageSupplier parametrageSupplier = parametrageSupplierRepository.findById(view.getId()).orElse(null);
        if (parametrageSupplier != null) {
            parametrageSupplier.setCodeMedtier(view.getCodeMedtier());
            parametrageSupplier.setCodeFinance(view.getCodeFinance());
            parametrageSupplier.setDescription1(view.getDescription1());
            parametrageSupplier.setType(view.getType());
            parametrageSupplier.setCurrency(view.getCurrency());
            parametrageSupplierRepository.save(parametrageSupplier);
        } else {
            throw new RuntimeException("Aucun Client trouvé");
        }
    }

}
