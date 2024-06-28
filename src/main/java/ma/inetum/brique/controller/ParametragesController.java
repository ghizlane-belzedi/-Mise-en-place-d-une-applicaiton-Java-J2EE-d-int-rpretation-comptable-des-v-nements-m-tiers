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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ma.inetum.brique.bean.ParamView;
import ma.inetum.brique.services.ParametrageService;
import ma.inetum.brique.utilities.DatatableConverter;

@Controller
@RequestMapping("/parametrages")
public class ParametragesController {

    @Autowired
    ParametrageService parametrageService;


    @ModelAttribute
    public void addAttributes(Model model) {
        List<String> categories = parametrageService.getCategories();
        model.addAttribute("categories",categories);
    }

    @GetMapping("/")
    public String index(Model model) {
        List<ParamView> param = parametrageService.getAll();
        model.addAttribute("parametre", param);
        return "parametrages/index";
    }
    @RequestMapping(value = "findall", method = RequestMethod.POST)
    public @ResponseBody DataTablesOutput<ParamView> findAll(HttpServletRequest request) {
        DataTablesInput input = DatatableConverter.convert(request);
        return parametrageService.getAll(input);
    }
    @GetMapping("/indexCategorie/{categorie}")
    public String indexCategorie(Model model, @PathVariable String categorie) {
        List<ParamView> param = parametrageService.findallByCategorie(categorie);
        model.addAttribute("categorie", categorie);
        model.addAttribute("parametre", param);
        return "parametrages/indexCategorie";
    }
    @RequestMapping(value = "findallByCategorie/{categorie}", method = RequestMethod.POST)
    public @ResponseBody DataTablesOutput<ParamView> findallByCategorie(HttpServletRequest request, @PathVariable String categorie) {
        DataTablesInput input = DatatableConverter.convert(request);
        return parametrageService.findallByCategorie(input, categorie);
    }


    @RequestMapping(value = "/add/{categorie}", method = RequestMethod.GET)
    public String add(Model model, @PathVariable String categorie) {
        model.addAttribute("parametre", new ParamView());
        model.addAttribute("categorie", categorie);
        return "parametrages/add";
    }

    @PostMapping("/add/{categorie}")
    public String addParametre(@Valid @ModelAttribute("parametre") ParamView paramView, BindingResult result, Model model, @PathVariable String categorie ) {
        List<String> errors = new ArrayList<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
            return "parametrages/add";
        } else {
            parametrageService.add(paramView, categorie);
            model.addAttribute("success", true);
            return "redirect:/parametrages/indexCategorie/" + categorie;

        }
    }

    @GetMapping("/edit/{id}")
    public String editGet(Model model, @PathVariable Long id ) {
        ParamView parametre = parametrageService.findById(id);
        model.addAttribute("parametre", parametre);
        model.addAttribute("paramId", id);
        return "parametrages/edit";
    }

    @PostMapping("/edit")
    public String editPost(@RequestBody ParamView paramView, BindingResult result, Model model){
        List<String> errors = new ArrayList<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
            return "parametrages/edit";
        } else {
            try{
                parametrageService.edit(paramView);
                model.addAttribute("success", true);
                return "redirect:/parametrages/indexCategorie/" + paramView.getCategorie();
            }
            catch (Exception e) {
                return "Erreur";
            }
        }
    }
}