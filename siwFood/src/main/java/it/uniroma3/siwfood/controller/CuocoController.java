package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siwfood.model.Credenziali;
import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Persona;
import it.uniroma3.siwfood.service.CredenzialiService;
import it.uniroma3.siwfood.service.CuocoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class CuocoController {

    @Autowired
    private CuocoService cuocoService;
    
    @Autowired 
    private CredenzialiService credenzialiService;

    @GetMapping("/profili")
    public String profili(Model model) {
        model.addAttribute("cuochi", cuocoService.findAll());
        return "profili";
    }

    @GetMapping("/profilo/{id}")
    public String profiloConUsername(@PathVariable("id")Long id, Model model) {

        UserDetails userLoggato = null;
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            userLoggato = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userLoggato!=null){
                Persona persona = credenzialiService.findByUsername(userLoggato.getUsername()).getPersona();
                if(persona.getId()==id){
                    model.addAttribute("isMyProfile", true);
                }
            }
        }

        model.addAttribute("cuoco", cuocoService.findById(id));
        return "profilo";
    }
    
    @GetMapping("/findProfilo/{username}")
    public String getMethodName(@PathVariable("username")String username) {
        return "redirect:/profilo/"+credenzialiService.findByUsername(username).getPersona().getId();
    }
    
    
}
