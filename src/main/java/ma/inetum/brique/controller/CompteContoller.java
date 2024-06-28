package ma.inetum.brique.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ma.inetum.brique.bean.CompteView;
import ma.inetum.brique.bean.FluxView;
import ma.inetum.brique.bean.SchemaEditView;
import ma.inetum.brique.bean.UserEditDto;
import ma.inetum.brique.services.CompteService;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.utilities.DatatableConverter;
import ma.inetum.brique.validator.SchemaComptableValidator;

@Controller
@RequestMapping("/compte")
public class CompteContoller {
	@Autowired
	ParametrageFluxService fluxService;
	@Autowired
	CompteService compteService;
	@Autowired
	SchemaComptableValidator schemaComptableValidator;
	@ModelAttribute
	public void addAttributes(Model model) {
		Map<String, String> flux = fluxService.getAll().stream()
				.collect(Collectors.toMap(FluxView::getCode, FluxView::getNom, (elem1, elem2) -> elem2, LinkedHashMap::new ));
		model.addAttribute("flux", flux);
	}
	@GetMapping("/")
	public String index(Model model) {
		return "compte/index";
	}
	@RequestMapping(value = "findall", method = RequestMethod.POST)
	public @ResponseBody DataTablesOutput<CompteView> findAll(HttpServletRequest request) {
		DataTablesInput input = DatatableConverter.convert(request);
		return compteService.getAll(input);
	}
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("schema", new SchemaEditView());
		return "compte/add";
	}
	@PostMapping("/add")
	public String addSchema(@Valid @ModelAttribute("schema") SchemaEditView schema, BindingResult result, Model model ) {
		List<String> errors = new ArrayList<>();
		schemaComptableValidator.validate(schema, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
			return "compte/add";
		} else {
			compteService.add(schema);
			model.addAttribute("success", true);
			return "redirect:/compte/";
		}
	}
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Long id ) {
		model.addAttribute("schema", compteService.findById(id));
		model.addAttribute("schemaId", id);
		return "compte/add";
	}
	@PostMapping("/edit")
	public String editSchema(@Valid @ModelAttribute("compte") SchemaEditView compte, BindingResult result, Model model ) {
		List<String> errors = new ArrayList<>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
			return "compte/add";
		} else {
			compteService.edit(compte);
			model.addAttribute("success", true);
			return "redirect:/compte/";
		}
	}
}
