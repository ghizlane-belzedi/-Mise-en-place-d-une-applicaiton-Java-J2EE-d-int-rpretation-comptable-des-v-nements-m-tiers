package ma.inetum.brique.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ma.inetum.brique.bean.PieceComptableView;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.services.PieceComptableService;
import ma.inetum.brique.utilities.DatatableConverter;

@Controller
public class VehiculeOrderController extends BaseController{
@Autowired
PieceComptableRepository pieceComptableRepository;
@Autowired 
PieceComptableService comptableService;
@GetMapping("/vehicule-order")
public String voh(Model model) {
	List<PieceComptable> pieces = pieceComptableRepository.findAll();
	List<PieceComptableView> view = new ArrayList<>();
	for(PieceComptable piece : pieces) {
		PieceComptableView p = new PieceComptableView();
		p.setCodeJournale(piece.getCodeJournale());
		p.setDate(piece.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
		p.setDescription(piece.getEnteteDoc());
		p.setId(piece.getId());
		p.setNumeroPiece(piece.getNumeroPiece());
		view.add(p);
	}
	model.addAttribute("pieces", view);
	return "piece/index";
}
@RequestMapping(value = "findall", method = RequestMethod.POST)
public @ResponseBody DataTablesOutput<PieceComptableView> findAll(HttpServletRequest request) {
	DataTablesInput input = DatatableConverter.convert(request);
	return comptableService.getAll(input, getFlux());
}
@GetMapping("/costing")
public String coh(Model model) {
	List<PieceComptable> pieces = pieceComptableRepository.findAll();
	List<PieceComptableView> view = new ArrayList<>();
	for(PieceComptable piece : pieces) {
		PieceComptableView p = new PieceComptableView();
		p.setCodeJournale(piece.getCodeJournale());
		p.setDate(piece.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
		p.setDescription(piece.getEnteteDoc());
		p.setId(piece.getId());
		p.setNumeroPiece(piece.getNumeroPiece());
		view.add(p);
	}
	model.addAttribute("pieces", view);
	return "piece/index";
}
}
