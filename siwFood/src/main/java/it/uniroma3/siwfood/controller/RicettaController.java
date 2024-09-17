package it.uniroma3.siwfood.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Ingrediente;
import it.uniroma3.siwfood.model.Persona;
import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.model.Tipologia;
import it.uniroma3.siwfood.service.CredenzialiService;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.IngredienteService;
import it.uniroma3.siwfood.service.RicettaService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RicettaController {

    @Autowired
    private RicettaService ricettaService;
    @Autowired
    private CuocoService cuocoService;
    @Autowired
    private CredenzialiService credenzialiService;
    @Autowired 
    private IngredienteService ingredienteService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("antipasti", ricettaService.findByTipologia(Tipologia.Antipasto));
        model.addAttribute("primi", ricettaService.findByTipologia(Tipologia.Primo));
        model.addAttribute("secondi", ricettaService.findByTipologia(Tipologia.Secondo));
        model.addAttribute("dolci", ricettaService.findByTipologia(Tipologia.Dolce));
        return "index";
    }
    
    @GetMapping("/login")
    public String getFormLogin() {
        return "formLogin";
    }

    @GetMapping("/profilo/{id}/newRicetta")
    public String getFormNewRicetta(@PathVariable("id")Long id, Model model) {
        model.addAttribute("cuoco", cuocoService.findById(id));
        model.addAttribute("ricetta", new Ricetta());
        model.addAttribute("tipologie", Tipologia.values());
        return "formNewRicetta";
    }

    @PostMapping("/profilo/{id}/newRicetta")
    public String newRicetta(@PathVariable("id")Long id, @ModelAttribute("ricettta")Ricetta ricetta) {
        Cuoco cuoco = cuocoService.findById(id);
        ricetta.setCuoco(cuoco);
        ricetta=ricettaService.save(ricetta);
        return "redirect:/ricetta/"+ricetta.getId();
    }

    @GetMapping("/ricetta/{id}")
    public String ricetta( @PathVariable("id")Long id, Model model) {
        Ricetta ricetta = ricettaService.findById(id);
        model.addAttribute("cuoco", ricetta.getCuoco());
        model.addAttribute("ricetta", ricetta);

        UserDetails userLoggato = null;
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            userLoggato = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userLoggato!=null){
                Persona persona = credenzialiService.findByUsername(userLoggato.getUsername()).getPersona();
                if(persona.getId()==ricetta.getCuoco().getId()){
                    model.addAttribute("isMyRicetta", true);
                }
            }
        }

        return "ricetta";
    }

    @GetMapping("/ricetta/{id}/newIngrediente")
    public String getFormNewIngrediente(@PathVariable("id")Long id, Model model) {
        Ricetta ricetta = ricettaService.findById(id);
        model.addAttribute("cuoco", ricetta.getCuoco());
        model.addAttribute("ricetta", ricetta);
        model.addAttribute("ingrediente", new Ingrediente());
        return "formNewIngrediente";
    }
    
    @PostMapping("/ricetta/{id}/newIngrediente")
    public String newIngrediente(@PathVariable("id")Long id, @ModelAttribute("ingrediente")Ingrediente ingrediente) {
        Ricetta ricetta = ricettaService.findById(id);
        ingrediente.setRicetta(ricetta);
        ingredienteService.save(ingrediente);
        return "redirect:/ricetta/"+id;
    }
    

}
