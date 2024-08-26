package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.DipendenteValidator;
//import it.uniroma3.siw.controller.validator.EventoValidator;
import it.uniroma3.siw.model.Dipendente;
import it.uniroma3.siw.service.DipendenteService;
import jakarta.validation.Valid;

@Controller
public class DipendenteController {

	@Autowired
	private DipendenteService dipendenteService;
	@Autowired
	private DipendenteValidator dipendenteValidator;
	
	@GetMapping("/admin/indexDipendente")
	public String indexDipendente() {
		return "admin/indexDipendente.html";
	}
	
	@PostMapping("/admin/dipendente")
	public String newDipendente(@Valid @ModelAttribute("dipendente") Dipendente dipendente, BindingResult bindingResult,Model model) {
		
		this.dipendenteValidator.validate(dipendente, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.dipendenteService.save(dipendente); 
			model.addAttribute("dipendente", dipendente);
			return "redirect:/dipendente";
		} else {
			return "admin/formNewDipendente.html"; 
		}
	}
	
	@GetMapping("/dipendente")
	public String getDipendenti(Model model) {
		model.addAttribute("dipendenti", this.dipendenteService.findAll());
		return "dipendente.html";
	}
	
	@GetMapping("/admin/formNewDipendente")
	public String formNewDipendente(Model model) {
		model.addAttribute("dipendente", new Dipendente());
		return "admin/formNewDipendente.html";
	}
	
	@GetMapping("/admin/manageDipendente")
	public String manageDipendente(Model model) {
		model.addAttribute("dipendenti", this.dipendenteService.findAll());
		return "admin/manageDipendente.html";
	}
	
	
	@GetMapping("/admin/dipendente/{id}")
    public String deleteDipendente(@PathVariable("id") Long id, Model model) {
        Dipendente dipendente = dipendenteService.findById(id);
        if (dipendente != null) {
            dipendenteService.delete(dipendente);
            // Redirect alla pagina dell'indice dei servizi dopo la cancellazione
            return "redirect:/dipendente";
        } else {
            // Nel caso in cui il servizio non esista
            model.addAttribute("messaggioErrore", "Servizio non trovato");
            return "admin/indexDipendente.html";
            }
        }
}

