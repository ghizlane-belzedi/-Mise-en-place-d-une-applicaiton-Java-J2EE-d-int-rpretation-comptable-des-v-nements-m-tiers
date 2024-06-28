package ma.inetum.brique.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ma.inetum.brique.bean.FluxView;
import ma.inetum.brique.bean.ListString;
import ma.inetum.brique.bean.PieceComptableDetailsView;
import ma.inetum.brique.bean.SimulationDetailsView;
import ma.inetum.brique.bean.SimulationView;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.model.principale.SimulationState;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.services.PieceComptableService;
import ma.inetum.brique.services.SimulationService;
import ma.inetum.brique.utilities.Constantes;
import ma.inetum.brique.utilities.DatatableConverter;
import ma.inetum.brique.utilities.ExcelGenerator;
import ma.inetum.brique.validator.SimulationValidator;

@Controller
@RequestMapping("/simulation")
public class SimulationController extends BaseController{

	@Autowired
	ParametrageFluxService fluxService;
	@Autowired
	SimulationService simulationService;
	@Autowired
	SimulationValidator simulationValidator;
	@Autowired
	PieceComptableService pieceComptableService;
	@ModelAttribute
	public void addAttributes(Model model) {
		Map<String, String> flux = fluxService.getAll().stream()
				.filter(e -> getFlux().contains(e.getCode()) && !Constantes.FLUX_VEH_ORD.equalsIgnoreCase(e.getCode()))
				.collect(Collectors.toMap(FluxView::getCode, FluxView::getNom, (elem1, elem2) -> elem2, LinkedHashMap::new ));
		List<String> statuts = List.of(SimulationState.values()).stream().map(e -> e.name()).collect(Collectors.toList());

		model.addAttribute("flux", flux);
		model.addAttribute("statuts", statuts);
	}

	@GetMapping("/")
	public String index(Model model) {
//		List<SimulationView> simulations = simulationService.getAll();
//		model.addAttribute("simulations", simulations);
		return "simulation/index";
	}

	@GetMapping("/details")
	public String details(Model model, @PathVariable Long id) {
//		SimulationDetailsView simulation = simulationService.findById(id);
//		model.addAttribute("simulation", simulation);
		return "simulation/details";
	}
	@RequestMapping(value = "findall", method = RequestMethod.POST)
	public @ResponseBody DataTablesOutput<SimulationView> findAll(HttpServletRequest request) {
		DataTablesInput input = DatatableConverter.convert(request);
		return simulationService.getAll(input, getFlux());
	}
	@GetMapping("/add")
	public String ajouter(Model model) {
		model.addAttribute("simulation", new SimulationDetailsView());
		model.addAttribute("errors", new HashMap<>());
		return "simulation/add";
	}

	@GetMapping("/{action}")
	public String generer(@PathVariable String action,
						  Model model,
						  @Valid @ModelAttribute("simulation") SimulationDetailsView simulation,
						  BindingResult result) throws Exception {
		Map<String, String> errors = new HashMap<>();
		ListString resultatGeneration = null;
		Map<Boolean, Object> resultatSimulation = null;
		model.addAttribute("simulation", simulation);
		simulationValidator.validate(simulation, result);
		if(result.hasErrors()) {
			model.addAttribute("hasErrors", true);
			return "simulation/add";
		}
		if(!getFlux().contains(simulation.getFlux()))
		{
			throw new Exception("Vous n'avez pas le droit de simuletr ce flux");
		}
		resultatGeneration = simulationService.generer(simulation.getFlux(), LocalDate.parse(simulation.getDateArreter()));
		model.addAttribute("resultatGeneration", resultatGeneration);
		if ("simuler".equals(action)&&resultatGeneration.getDetails().isEmpty() ) {
			result.rejectValue("isEmpty", "", "La liste générée est vide");
			return "simulation/add";
		}
		if ("generer".equals(action)) {

		} else if ("simuler".equals(action)) {
			resultatSimulation = simulationService.simuler(simulation.getFlux(),
					simulation.getDateArreter(), simulation);
			model.addAttribute("resultatSimulation", resultatSimulation);
		}
		return "simulation/add";

	}
	@GetMapping("/generer/{id}")
	public String generer(@PathVariable Long id, Model model, @Valid @ModelAttribute("simulation") SimulationDetailsView simulation,
						  BindingResult result ) throws Exception, ExceptionFonctionnelle {
		simulation =  simulationService.findById(id);
		if(simulation == null) {
			throw new Exception("Simulation Vide");
		} else {
			if(!getFlux().contains(simulation.getFluxCode()))
			{
				throw new Exception("Vous n'avez pas le droit de consulter cette chargement");
			}
			try {
				simulation = simulationService.generer(id);
				model.addAttribute("simulation", simulation);				
			} catch (Exception ex) {
				result.rejectValue("comment", "ERRRRR", "ERRRRRRR");
				return "simulation/consult";				
			}
			if(simulation.getComment() != null ) {
				result.rejectValue("comment", "", simulation.getComment());
				return "simulation/consult";
			}
			return "redirect:/simulation/";
		}

	}
	@GetMapping("/consult/{id}")
	public String consulter(@PathVariable Long id, Model model ) throws Exception {
		List<String> errors = new ArrayList<>();
		SimulationDetailsView simulation = simulationService.findById(id);
		if(!getFlux().contains(simulation.getFluxCode()))
		{
			throw new Exception("Vous n'avez pas le droit de consulter cette chargement");
		}
		model.addAttribute("simulation", simulation);
		return "simulation/consult";

	}
	@GetMapping("/piece/{id}")
	public String piece(@PathVariable Long id, Model model ) {
		PieceComptableDetailsView piece = pieceComptableService.findById(id);
		model.addAttribute("piece", piece);
		return "simulation/piece";

	}
	@GetMapping("/annuler/{id}")
	public String annuler(@PathVariable Long id, Model model ) throws Exception {
		List<String> errors = new ArrayList<>();
		var simulation =  simulationService.findById(id);
		if(simulation == null) {
			throw new Exception("Simulation Vide");
		} else {
			if(!getFlux().contains(simulation.getFluxCode()))
			{
				throw new Exception("Vous n'avez pas le droit de consulter cette chargement");
			}
			simulation = simulationService.annuler(id);
			model.addAttribute("simulation", simulation);
			if(simulation.getComment() != null ) {
				return "simulation/consult";
			}
			return "redirect:/simulation/";
		}
	}
	@GetMapping("/exporter/{id}")
    public void exportToExcel(@PathVariable Long id, HttpServletResponse response) throws Exception {
		if(id == null) {
			throw new Exception("Identifiant absent");
		}
		response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=simulation_" + id + ".xlsx";
        response.setHeader(headerKey, headerValue);
        ExcelGenerator generator = new ExcelGenerator();
        generator.generateEcritures(simulationService.getAllPieces(id), response);
    }  
}
