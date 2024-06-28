package ma.inetum.brique.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ma.inetum.brique.bean.FluxView;
import ma.inetum.brique.bean.UserAddDto;
import ma.inetum.brique.bean.UserAllDto;
import ma.inetum.brique.bean.UserEditDto;
import ma.inetum.brique.model.principale.ERole;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.services.ProfileService;
import ma.inetum.brique.services.UserService;
import ma.inetum.brique.validator.UserValidator;

@Controller
@RequestMapping("/user")

public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	ProfileService profileService;
	@Autowired
	ParametrageFluxService fluxService;
	@Autowired
	UserValidator userValidator;
	
	@ModelAttribute
	public void addAttributes(Model model) {
		List<String> roles = roles = List.of(ERole.values()).stream().map(e -> e.toString()).collect(Collectors.toList());
		Map<String, String> flux = fluxService.getAll().stream()
				.collect(Collectors.toMap(FluxView::getCode, FluxView::getNom, (elem1, elem2) -> elem2, LinkedHashMap::new ));

		model.addAttribute("flux", flux);
		model.addAttribute("profiles", profileService.getAll());
	}
	@GetMapping("/")
	public String getAllContrats(Model model) {
		List<UserAllDto> list = userService.getAll();
		model.addAttribute("users", list);
 		return "user/index";
	}
	
	@GetMapping("/add")
	public String addContratGet(Model model) {
		model.addAttribute("user", new UserAddDto());
		return "user/add";
	}
	@PreAuthorize("USER_UPDATE")
	@PostMapping("/add")
	public String addContratPost(@Valid @ModelAttribute("user") UserAddDto user, BindingResult result, Model model ) {
		List<String> errors = new ArrayList<>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
			if(user.getPassword() != null  && !user.getPassword().equals(user.getRepassword())) {
				result.rejectValue("repassword", "","La valeur des mots de pass ne correspand pas");
			}
			return "user/add";
		} else {
			userService.add(user);
			model.addAttribute("success", true);
			return "redirect:/user/";
		}

	}
	@PreAuthorize("USER_UPDATE")
	@GetMapping("/edit/{id}")
	public String editContactGet(Model model, @PathVariable Long id ) {
		model.addAttribute("user", userService.findById(id));
		model.addAttribute("userId", id);
		return "user/edit";
	}
	@PostMapping("/edit")
	public String editContactPost(@Valid @ModelAttribute("user") UserEditDto user, BindingResult result, Model model ) {
		List<String> errors = new ArrayList<>();
		userValidator.validateUser(user, errors);
		if (!errors.isEmpty()) {
			result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
			return "user/edit";
		} else {
			userService.edit(user);
			model.addAttribute("success", true);
			return "redirect:/user/";
		}
	}
	@PreAuthorize("USER_UPDATE")
	@GetMapping("/consult/{id}")
	public String consultContactGet(Model model, @PathVariable Long id ) {
		model.addAttribute("user", userService.findById(id));
		return "user/consult";
	}
}
