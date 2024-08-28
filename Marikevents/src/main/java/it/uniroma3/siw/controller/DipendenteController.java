package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.controller.validator.DipendenteValidator;
//import it.uniroma3.siw.controller.validator.EventoValidator;
import it.uniroma3.siw.model.Dipendente;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.service.DipendenteService;
import it.uniroma3.siw.service.ImageService;
import jakarta.validation.Valid;

@Controller
public class DipendenteController {

	@Autowired
	private DipendenteService dipendenteService;
	
	@Autowired
	private DipendenteValidator dipendenteValidator;
	
	@Autowired
	private ImageService imageService;	
	
	
	@GetMapping("/admin/indexDipendente")
	public String indexDipendente() {
		return "admin/indexDipendente.html";
	}
	
	/*@PostMapping("/admin/dipendente")
	public String newDipendente(@Valid @ModelAttribute("dipendente") Dipendente dipendente, BindingResult bindingResult,Model model) {
		
		this.dipendenteValidator.validate(dipendente, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.dipendenteService.save(dipendente); 
			model.addAttribute("dipendente", dipendente);
			return "redirect:/dipendente";
		} else {
			return "admin/formNewDipendente.html"; 
		}
	}*/
	
	@PostMapping("/admin/dipendente")
	public String newDipendente(@Valid @ModelAttribute("dipendente") Dipendente dipendente,
	                            BindingResult bindingResult,
	                            @RequestParam("imageFile") MultipartFile imageFile,
	                            Model model) {
	    // Valida l'oggetto Dipendente
	    this.dipendenteValidator.validate(dipendente, bindingResult);

	    if (!bindingResult.hasErrors()) {
	        // Crea e salva l'immagine se è presente
	        if (!imageFile.isEmpty()) {
	            try {
	                // Crea un nuovo oggetto Image
	                Image image = new Image();
	                image.setBytes(imageFile.getBytes()); // Imposta i byte dell'immagine
	                
	                // Salva l'immagine nel database
	                this.imageService.saveImage(image); // Assicurati di avere un ImageService per salvare l'immagine
	                
	                // Associa l'immagine al dipendente
	                dipendente.setImage(image);
	            } catch (IOException e) {
	                e.printStackTrace();
	                model.addAttribute("errorMessage", "Errore nel caricamento dell'immagine.");
	                return "admin/formNewDipendente.html";
	            }
	        }

	        // Salva il dipendente
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

