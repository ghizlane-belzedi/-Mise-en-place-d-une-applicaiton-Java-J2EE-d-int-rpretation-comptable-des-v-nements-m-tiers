package ma.inetum.brique.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ma.inetum.brique.bean.ProfileEditDto;
import ma.inetum.brique.model.principale.AppRole;
import ma.inetum.brique.repository.principale.AppRoleRepository;
import ma.inetum.brique.services.ProfileService;
import ma.inetum.brique.validator.ProfilValidator;

@Controller
//@Secured("PROFILE")
@RequestMapping("/profile")
public class ProfilController {
	@Autowired
	ProfilValidator profilValidator; 
	@Autowired
	ProfileService profilService;
	@Autowired
	AppRoleRepository roleRepository;
	
	@ModelAttribute 
	public void addAttributes(Model model) {
		
	}
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String profils(Model model) {
    	model.addAttribute("profiles", profilService.getAll());
    	model.addAttribute("roles", roleRepository.findAll().stream().collect(Collectors.toMap(AppRole::getRoleId, AppRole::getRoleName, (elem1, elem2) -> elem2 )));
    	return "profil/index";
    }
//    @RequestMapping(value = "/profils", method = RequestMethod.GET)
//	@ResponseBody
//	public String profils(HttpServletRequest request, HttpServletResponse response, Model model) {
//		return new Gson().toJson(profilService.searchProfil(new DataTableRequest<ProfilBean>(request)));
//	}
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String profil(Model model) {
    	model.addAttribute("profile", new ProfileEditDto());
        
        return "profil/add";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String modifProfil(Model model, @PathVariable Long id) {
    	model.addAttribute("profile", profilService.findById(id));
        return "profil/edit";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addprofil(Model model, @Valid @ModelAttribute("profile") ProfileEditDto profile, BindingResult result) {
    	List<String> errors = new ArrayList<>();
    	if(profilValidator.validateProfil(profile, errors)) {
    		profilService.add(profile);
    		model.addAttribute("result", true);
    	} else {
    		model.addAttribute("result", false);
    		model.addAttribute("errors", errors);
    	}
    	model.addAttribute("profile", profile);
        return "profil/add";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(Model model, @Valid @ModelAttribute("profile") ProfileEditDto profile, BindingResult result) {
    	List<String> errors = new ArrayList<>();
    	
    	if(profilValidator.validateProfil(profile, errors)) {
    		profilService.edit(profile);
    		model.addAttribute("result", true);
    	} else {
    		model.addAttribute("result", false);
    		model.addAttribute("errors", errors);
    	}
    	model.addAttribute("profile", profile);
        return "profil/edit";
    }
    @RequestMapping(value = "/consult/{id}", method = RequestMethod.GET)
    public String consultProfil(Model model, @PathVariable Long id) {
    	model.addAttribute("profile", profilService.findById(id));
        return "profil/consult";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String deleteProfil(Model model, @RequestParam(name = "id", required = false) Long id) {
    	if(profilService.deleteById(id)) {
    		return "true";
    	} else {
    		return "false";
    	}
        
    }
}
