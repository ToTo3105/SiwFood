package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwfood.model.Credenziali;
import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Immagine;
import it.uniroma3.siwfood.model.RuoloUtente;
import it.uniroma3.siwfood.service.CredenzialiService;

@Controller
public class CredenzialiController {

    @Autowired
    private CredenzialiService credenzialiService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/register")
    public String getFormNewCuoco(Model model) {
        model.addAttribute("cuoco", new Cuoco());
        model.addAttribute("credenziali", new Credenziali());
        return "formNewCuoco";
    }

    @PostMapping("/register")
    public String newCuoco(@ModelAttribute("cuoco")Cuoco cuoco, @ModelAttribute("credenziali")Credenziali credenziali, Model model) {
        /*Immagine immagine = new Immagine();
        try{
            immagine.setBytes(file.getBytes());
        }catch(Exception e){
            model.addAttribute("errore", "errore nel caricamento");
            return "formNewCuoco";
        }
        cuoco.setImmagine(immagine);*/
        credenziali.setPersona(cuoco);
        credenziali.setPassword(passwordEncoder.encode(credenziali.getPassword()));
        credenziali.setRuoloUtente(RuoloUtente.CUOCO);
        credenzialiService.save(credenziali);
        return "redirect:/";
    }


    @GetMapping("/success")
    public String successLogin() {
        return "redirect:/";
    }

    @GetMapping("/failure")
    public String failureLogin(Model model) {
        model.addAttribute("errorLogin", "Username o password errati!");
        return "formLogin";
    }
    
    
}
