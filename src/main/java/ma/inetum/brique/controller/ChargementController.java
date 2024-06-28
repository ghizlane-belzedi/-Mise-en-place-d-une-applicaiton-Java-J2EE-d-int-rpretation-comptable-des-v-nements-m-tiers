package ma.inetum.brique.controller;

import java.util.Comparator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ma.inetum.brique.bean.ChargementDetailsView;
import ma.inetum.brique.bean.ChargementView;
import ma.inetum.brique.bean.FluxView;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.ParametrageFLux;
import ma.inetum.brique.services.ChargementService;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.services.SimulationService;
import ma.inetum.brique.utilities.DatatableConverter;

@Controller
@RequestMapping("/chargement")
public class ChargementController extends BaseController {
	@Autowired
	ParametrageFluxService fluxService;
	@Autowired
	SimulationService simulationService;
	@Autowired
	ChargementService chargementService;
	@ModelAttribute
	public void addAttributes(Model model) {
		Comparator<ParametrageFLux> sortByANomAsc = Comparator.comparing(ParametrageFLux::getNom);
		Map<String, String> flux = fluxService.getAll().stream()
				.filter(e -> getFlux().contains(e.getCode()))
				.collect(Collectors.toMap(FluxView::getCode, FluxView::getNom, (elem1, elem2) -> elem2, LinkedHashMap::new));
		List<String> statuts = List.of(ChargementState.values()).stream().map(e -> e.toString()).collect(Collectors.toList());
		model.addAttribute("flux", flux);
		model.addAttribute("statuts", statuts);
	}
	@GetMapping("/")
	public String index(Model model) {
//		List<ChargementView> chargements = chargementService.getAll();
//		model.addAttribute("chargements", chargements);
		return "chargement/index";
	}
	@RequestMapping(value = "findall", method = RequestMethod.POST)
	public @ResponseBody DataTablesOutput<ChargementView> findAll(HttpServletRequest request) {
		DataTablesInput input = DatatableConverter.convert(request);
		return chargementService.getAll(input, getFlux());
	}
	@GetMapping("/consult/{id}")
	public String details(Model model, @PathVariable Long id) throws Exception {
		ChargementDetailsView chargement = chargementService.findById(id);
		if(!getFlux().contains(chargement.getFluxCode()))
		{
			throw new Exception("Vous n'avez pas le droit de consulter cette chargement");
		}
		model.addAttribute("chargement", chargement);
		return "chargement/consult";
	}
	@GetMapping("/situation-chargement")
	public String situation(Model model) {
		List<ChargementView> chargementViews = chargementService.situationChargement();
		model.addAttribute("chargements", chargementViews);
		return "chargement/situation";
	}
	@GetMapping("/start-chargement")
	public String launch(Model model, @RequestParam(name = "fluxCode") String fluxCode) {
		model.addAttribute("resultat", chargementService.launch(fluxCode, getFlux()));
		return "chargement/index";
	}
}

