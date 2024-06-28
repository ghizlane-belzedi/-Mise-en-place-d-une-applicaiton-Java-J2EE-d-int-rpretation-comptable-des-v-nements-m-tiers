package ma.inetum.brique.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

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

import ma.inetum.brique.bean.PieceComptableDetailsView;
import ma.inetum.brique.bean.PieceComptableView;
import ma.inetum.brique.mapper.PieceComptableMapper;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.services.PieceComptableService;
@Service
public class PieceComptableServiceImpl implements PieceComptableService{
	private static final Logger log = LoggerFactory.getLogger(PieceComptableServiceImpl.class);
	@Autowired
	PieceComptableRepository pieceComptableRepository;
	PieceComptableMapper mapper = PieceComptableMapper.getInstance();
	@Override
	public PieceComptableDetailsView findById(Long id) {
		PieceComptable pieceComptable = pieceComptableRepository.findById(id).get();
		PieceComptableDetailsView view = mapper.entityTobeanDetails(pieceComptable);
		return view;
	}

	@Override
	public List<PieceComptableView> getAll() {
		List<PieceComptableView> pieces = new ArrayList<>();
		List<PieceComptable> pieceComptables = pieceComptableRepository.findAll();
		if(pieceComptables != null) {
			for(PieceComptable piece : pieceComptables) {
				pieces.add( mapper.entityTobean(piece));
			}
		}
		return pieces;
	}
	@Override
	public DataTablesOutput<PieceComptableView> getAll(DataTablesInput input, List<String> flux) {
		DataTablesOutput<PieceComptableView> view = new DataTablesOutput<>();
		String[] search = input.getSearch().getValue().split(";");
		Pageable pageable = PageRequest.of(input.getStart() / input.getLength(), input.getLength(), Direction.fromOptionalString(input.getOrder().get(0).getDir()).orElse(Direction.DESC), input.getColumns().get(input.getOrder().get(0).getColumn() ).getName());
		Specification<PieceComptable> specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (search.length > 0 && search[0] != null && !search[0].isBlank()) {	
				predicates.add(criteriaBuilder.like(root.join("flux").get("code"), search[0]));
			}
			try {
				if (search.length > 1 && search[1] != null && !search[1].isBlank()) {
					predicates.add(criteriaBuilder.equal(root.join("vehicule").get("sent"), Boolean.valueOf(search[1])));
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			if (search.length > 2 && search[2] != null && !search[2].isBlank()) {
				try {
					LocalDateTime dateDebut = LocalDate.parse(search[2]).atStartOfDay();
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), dateDebut));	
				} catch (Exception e) {
					log.error("Date debut n'est pas au format correct");
				}
			}
			if (search.length > 3 && search[3] != null && !search[3].isBlank()) {
				try {
					LocalDateTime dateFin = LocalDate.parse(search[3]).atStartOfDay();
					predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"), dateFin));	
				} catch (Exception e) {
					log.error("Date fin n'est pas au format correct");
				}
			}
			predicates.add( root.join("flux").get("code").in(flux));
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};
		Page<PieceComptable> pieces = pieceComptableRepository.findAll(specification, pageable);
		
		view.setData(pieces.stream().map(e -> mapper.entityTobean(e)).collect(Collectors.toList()));
		view.setDraw(input.getDraw());
		view.setError(null);
		view.setRecordsFiltered(pieceComptableRepository.count(specification));
		view.setRecordsTotal(pieceComptableRepository.count());
		return view;
	}
	@Override
	public List<PieceComptable> getAllVNFacturedNotDeliverd() {
		List<PieceComptable> liste = new ArrayList<>();
//		pieceComptableRepository.get
		return liste;
	}
}
