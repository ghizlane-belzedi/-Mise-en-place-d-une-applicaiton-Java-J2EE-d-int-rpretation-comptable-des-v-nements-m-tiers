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

import ma.inetum.brique.bean.CompteVersementBean;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.services.CompteVersementService;
import ma.inetum.brique.services.ModePaiementService;

@Controller
@RequestMapping("/compte-versement")
public class CompteVersementController {

    @Autowired
    private CompteVersementService compteVersementService;
    @Autowired
    private ModePaiementService modePaiementService;
    @ModelAttribute
    public void addAttributes(Model model) {
    	model.addAttribute("modes", modePaiementService.getAll());
    }

    @GetMapping("/")
    public String index(Model model) {
        List<CompteVersementBean> comptes = compteVersementService.getAll();
        model.addAttribute("comptes", comptes);
        return "compteVersement/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("compte", new CompteVersementBean());
        return "compteVersement/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("compte") CompteVersementBean compte, BindingResult result, Model model ) {
        if(compte.getSite() == null || compte.getSite().isBlank() ) {
        	result.rejectValue("site", null, "Code Site est obligatoire");
        }
        if(compte.getCompteGeneral() == null || compte.getCompteGeneral().isBlank() ) {
        	result.rejectValue("compteGeneral", null, "Compte General est obligatoire");
        }
        if(compte.getModePaiement() == null || compte.getModePaiement().isBlank() ) {
        	result.rejectValue("modePaiement", null, "Mode Paiement est obligatoire");
        }
        if (result.hasErrors()) {
            return "compteVersement/add";
        } else {
        	compteVersementService.add(compte);
            model.addAttribute("success", true);
            return "redirect:/compte-versement/";

        }
    }

    @GetMapping("/edit/{id}")
    public String editGet(Model model, @PathVariable Long id ) throws ExceptionFonctionnelle {
        CompteVersementBean compte = compteVersementService.findById(id);
        model.addAttribute("compte", compte);
        model.addAttribute("id", id);
        return "compteVersement/edit";
    }

    @PostMapping("/edit")
    public String editPost(@Valid @ModelAttribute("compte")  CompteVersementBean compte, BindingResult result, Model model) throws ExceptionFonctionnelle{
        if(compte.getSite() == null || compte.getSite().isBlank() ) {
        	result.rejectValue("site", null, "Code Site est obligatoire");
        }
        if(compte.getCompteGeneral() == null || compte.getCompteGeneral().isBlank() ) {
        	result.rejectValue("compteGeneral", null, "Compte General est obligatoire");
        }
        if(compte.getModePaiement() == null || compte.getModePaiement().isBlank() ) {
        	result.rejectValue("modePaiement", null, "Mode Paiement est obligatoire");
        }
        if (result.hasErrors()) {
        	return "compteVersement/edit";
        } else {
        	compteVersementService.edit(compte);
            model.addAttribute("success", true);
            return "redirect:/compte-versement/";
        }
    }

}
