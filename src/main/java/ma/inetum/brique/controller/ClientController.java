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

import ma.inetum.brique.bean.ClientBean;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @ModelAttribute
    public void addAttributes(Model model) {
    }

    @GetMapping("/")
    public String index(Model model) {
        List<ClientBean> clients = clientService.getAll();
        model.addAttribute("clients", clients);
        return "client/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("client", new ClientBean());
        return "client/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("client") ClientBean client, BindingResult result, Model model ) {
        if(client.getSite() == null || client.getSite().isBlank() ) {
        	result.rejectValue("site", null, "Code Site est obligatoire");
        }
        if(client.getCodeMedtier() == null || client.getCodeMedtier().isBlank() ) {
        	result.rejectValue("codeMedtier", null, "Compte Métier est obligatoire");
        }
        if(client.getCodeFinance() == null || client.getCodeFinance().isBlank() ) {
        	result.rejectValue("codeFinance", null, "Compte Finance est obligatoire");
        }
        if (result.hasErrors()) {
            return "client/add";
        } else {
        	try {
        		clientService.add(client);
                model.addAttribute("success", 1);
                return "redirect:/client/";
        	} catch (Throwable e) {
        		model.addAttribute("success", 0);
        		model.addAttribute("message", "Une erreur technique s'est produite merci de voir avec votre administrateur technique");
                return "client/add";
			}

        }
    }

    @GetMapping("/edit/{id}")
    public String editGet(Model model, @PathVariable Long id ) throws ExceptionFonctionnelle {
    	ClientBean client = clientService.findById(id);
        model.addAttribute("client", client);
        model.addAttribute("id", id);
        return "client/edit";
    }

    @PostMapping("/edit")
    public String editPost(@Valid @ModelAttribute("client")  ClientBean client, BindingResult result, Model model) {
    	if(client.getSite() == null || client.getSite().isBlank() ) {
        	result.rejectValue("site", null, "Code Site est obligatoire");
        }
        if(client.getCodeMedtier() == null || client.getCodeMedtier().isBlank() ) {
        	result.rejectValue("codeMedtier", null, "Compte Métier est obligatoire");
        }
        if(client.getCodeFinance() == null || client.getCodeFinance().isBlank() ) {
        	result.rejectValue("codeFinance", null, "Compte Finance est obligatoire");
        }
        if (result.hasErrors()) {
        	return "client/edit";
        } else {
        	try {
        		clientService.edit(client);
                model.addAttribute("success", true);
                return "redirect:/client/";
        	} catch (Throwable e) {
        		model.addAttribute("success", 0);
        		model.addAttribute("message", "Une erreur technique s'est produite merci de voir avec votre administrateur technique");
                return "client/edit";
			}
        }
    }

}
