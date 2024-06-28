package ma.inetum.brique.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ma.inetum.brique.bean.FluxEditView;
import ma.inetum.brique.bean.FluxView;
import ma.inetum.brique.services.ParametrageFluxService;

@Controller
@RequestMapping("/parametrage-flux")
public class ParametrageFluxController {

	@Autowired
	ParametrageFluxService parametrageFluxService;

	@ModelAttribute
	public void addAttributes(Model model) {


	}
	@GetMapping("/")
	public String getAllFlux(Model model) {
		List<FluxView> flux = parametrageFluxService.getAll();
		model.addAttribute("flux", flux);
		return "parametrageFlux/index";
	}
	@GetMapping("/edit/{id}")
	public String editContactGet(Model model, @PathVariable Long id ) {
		FluxEditView flux = parametrageFluxService.findById(id);
		model.addAttribute("flux", flux);
		model.addAttribute("fluxId", id);
		model.addAttribute("visibles", flux.getFields().stream().filter(e->e.isVisible()).count());
		return "parametrageFlux/edit";
	}
	@PostMapping("/edit")
	@ResponseBody
	public String editContactPost(@Valid @RequestBody FluxEditView flux, BindingResult result, Model model ) {
		List<String> errors = new ArrayList<>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
			return "parametrageFlux/edit";
		} else {
			try {
				parametrageFluxService.edit(flux);
				return "chargement avec succès";
			} catch (Exception e) {
				return "Erreur de chargement";
			}

		}
	}
	@GetMapping("/consult/{id}")
	public String consultContactGet(Model model, @PathVariable Long id ) {
		FluxEditView flux = parametrageFluxService.findById(id);
		model.addAttribute("flux", flux);
		model.addAttribute("visibles", flux.getFields().stream().filter(e->e.isVisible()).count());
		return "parametrageFlux/consult";
	}
}
