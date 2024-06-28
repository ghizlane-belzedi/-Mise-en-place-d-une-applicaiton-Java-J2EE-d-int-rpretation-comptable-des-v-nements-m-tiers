package ma.inetum.brique.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ma.inetum.brique.bean.CompteFacturationBean;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.services.CompteFacturationService;

@Controller
@RequestMapping("/compte-facturation")
public class CompteFacturationController {

    @Autowired
    CompteFacturationService compteFacturationService;

    @ModelAttribute
    public void addAttributes(Model model) {
    }

    @GetMapping("/")
    public String index(Model model) {
        List<CompteFacturationBean> comptes = compteFacturationService.getAll();
        model.addAttribute("comptes", comptes);
        return "compteFacturation/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("compte", new CompteFacturationBean());
        return "compteFacturation/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("compte") CompteFacturationBean compte, BindingResult result, Model model ) {
        if(compte.getSite() == null || compte.getSite().isBlank() ) {
        	result.rejectValue("site", null, "Code Site est obligatoire");
        }
        if(compte.getCompteGeneral() == null || compte.getCompteGeneral().isBlank() ) {
        	result.rejectValue("compteGeneral", null, "Compte General est obligatoire");
        }
        if (result.hasErrors()) {
            return "compteFacturation/add";
        } else {
        	try {
        		compteFacturationService.add(compte);
                model.addAttribute("success", 1);
                return "redirect:/compte-facturation/";
        	} catch (Throwable e) {
        		model.addAttribute("success", 0);
        		model.addAttribute("message", "Une erreur technique s'est produite merci de voir avec votre administrateur technique");
                return "compteFacturation/add";
			}

        }
    }

    @GetMapping("/edit/{id}")
    public String editGet(Model model, @PathVariable Long id ) throws ExceptionFonctionnelle {
        CompteFacturationBean compte = compteFacturationService.findById(id);
        model.addAttribute("compte", compte);
        model.addAttribute("id", id);
        return "compteFacturation/edit";
    }

    @PostMapping("/edit")
    public String editPost(@Valid @ModelAttribute("compte")  CompteFacturationBean compte, BindingResult result, Model model) {
        if(compte.getSite() == null || compte.getSite().isBlank() ) {
        	result.rejectValue("site", null, "Code Site est obligatoire");
        }
        if(compte.getCompteGeneral() == null || compte.getCompteGeneral().isBlank() ) {
        	result.rejectValue("compteGeneral", null, "Compte General est obligatoire");
        }
        if (result.hasErrors()) {
        	return "compteFacturation/edit";
        } else {
        	try {
        		compteFacturationService.edit(compte);
                model.addAttribute("success", true);
                return "redirect:/compte-facturation/";
        	} catch (Throwable e) {
        		model.addAttribute("success", 0);
        		model.addAttribute("message", "Une erreur technique s'est produite merci de voir avec votre administrateur technique");
                return "compteFacturation/edit";
			}
        }
    }

}
