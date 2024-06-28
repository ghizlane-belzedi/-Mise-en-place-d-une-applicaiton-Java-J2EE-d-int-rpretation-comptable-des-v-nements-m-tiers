package ma.inetum.brique.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ma.inetum.brique.bean.FluxView;
import ma.inetum.brique.bean.PieceComptableDetailsView;
import ma.inetum.brique.bean.PieceComptableView;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.services.PieceComptableService;
import ma.inetum.brique.utilities.DatatableConverter;

@Controller
@RequestMapping("/piece-comptable")
public class PieceComptableController  extends BaseController{
	@Autowired
	ParametrageFluxService fluxService;
	@Autowired
	PieceComptableService pieceComptableService;
	@ModelAttribute
	public void addAttributes(Model model) {
		Map<String, String> flux = fluxService.getAll().stream()
				.filter(e -> getFlux().contains(e.getCode()))
				.collect(Collectors.toMap(FluxView::getCode, FluxView::getNom, (elem1, elem2) -> elem2, LinkedHashMap::new ));
		List<String> statuts = List.of(ChargementState.values()).stream().map(e -> e.toString()).collect(Collectors.toList());

		model.addAttribute("flux", flux);
		model.addAttribute("statuts", statuts);
	}
	@GetMapping("/")
	public String index(Model model) {
//		List<PieceComptableView> pieces = pieceComptableService.getAll();
//		model.addAttribute("pieces", pieces);
		return "piece/index";
	}
	@RequestMapping(value = "findall", method = RequestMethod.POST)
	public @ResponseBody DataTablesOutput<PieceComptableView> findAll(HttpServletRequest request) {
		DataTablesInput input = DatatableConverter.convert(request);
		return pieceComptableService.getAll(input, getFlux());
	}
	@GetMapping("/consult/{id}")
	public String details(Model model, @PathVariable Long id) throws Exception {
		PieceComptableDetailsView piece = pieceComptableService.findById(id);
		if(!getFlux().contains(piece.getFluxCode()))
		{
			throw new Exception("Vous n'avez pas le droit de consulter cette chargement");
		}
		model.addAttribute("piece", piece);
		return "piece/consult";
	}

}