package ma.inetum.brique.controller;

import java.util.ArrayList;
import java.util.List;

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

import ma.inetum.brique.bean.ParamSupplierView;
import ma.inetum.brique.services.ParametrageSupplierService;
import ma.inetum.brique.utilities.DatatableConverter;

@Controller
@RequestMapping("/parametrageSupplier")
public class ParametrageSupplierController {

    @Autowired
    ParametrageSupplierService parametrageSupplierService;

    @ModelAttribute
    public void addAttributes(Model model) {
    }

    @GetMapping("/")
    public String index(Model model) {
        List<ParamSupplierView> fournisseur = parametrageSupplierService.getAll();
        model.addAttribute("fournisseur", fournisseur);
        return "parametrageSupplier/index";
    }

    @RequestMapping(value = "findall", method = RequestMethod.POST)
    public @ResponseBody DataTablesOutput<ParamSupplierView> findAll(HttpServletRequest request) {
        DataTablesInput input = DatatableConverter.convert(request);
        return parametrageSupplierService.getAll(input);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("fournisseur", new ParamSupplierView());
        return "parametrageSupplier/add";
    }

    @PostMapping("/add")
    public String addParametreSupplier(@Valid @ModelAttribute("fournisseur") ParamSupplierView paramSupplierView, BindingResult result, Model model ) {
        List<String> errors = new ArrayList<>();
        if(paramSupplierView.getCodeMedtier() == null || paramSupplierView.getCodeMedtier().isBlank() ) {
        	result.rejectValue("codeMedtier", null, "Code Métier est obligatoire"); 
        }
        if(paramSupplierView.getCodeFinance() == null || paramSupplierView.getCodeFinance().isBlank() ) {
        	result.rejectValue("codeFinance", null, "Code Finance est obligatoire");
        }
        if(paramSupplierView.getType() == null || paramSupplierView.getType().isBlank() ) {
        	result.rejectValue("type", null, "Type est obligatoire");
        }
        if (result.hasErrors()) {
//            result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
            return "parametrageSupplier/add";
        } else {
            parametrageSupplierService.add(paramSupplierView);
            model.addAttribute("success", true);
            return "redirect:/parametrageSupplier/";

        }
    }

    @GetMapping("/edit/{id}")
    public String editGet(Model model, @PathVariable Long id ) {
        ParamSupplierView fournisseur = parametrageSupplierService.findById(id);
        model.addAttribute("fournisseur", fournisseur);
        model.addAttribute("id", id);
        return "parametrageSupplier/edit";
    }

    @PostMapping("/edit")
    public String editPost(@Valid @ModelAttribute("fournisseur")  ParamSupplierView paramSupplierView, BindingResult result, Model model){
        List<String> errors = new ArrayList<>();
        if(paramSupplierView.getCodeMedtier() == null || paramSupplierView.getCodeMedtier().isBlank() ) {
        	result.rejectValue("codeMedtier", null, "Code Métier est obligatoire"); 
        }
        if(paramSupplierView.getCodeFinance() == null || paramSupplierView.getCodeFinance().isBlank() ) {
        	result.rejectValue("codeFinance", null, "Code Finance est obligatoire");
        }
        if(paramSupplierView.getType() == null || paramSupplierView.getType().isBlank() ) {
        	result.rejectValue("type", null, "Type est obligatoire");
        }
        if (result.hasErrors()) {
//            result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
            return "parametrageSupplier/edit";
        } else {
            parametrageSupplierService.edit(paramSupplierView);
            model.addAttribute("success", true);
            return "redirect:/parametrageSupplier/";
        }
    }

}
