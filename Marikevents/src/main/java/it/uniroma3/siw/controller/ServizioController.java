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

//import it.uniroma3.siw.controller.validator.EventoValidator;
import it.uniroma3.siw.controller.validator.ServizioValidator;
import it.uniroma3.siw.model.Servizio;
import it.uniroma3.siw.service.ServizioService;
import jakarta.validation.Valid;

@Controller
public class ServizioController {
	@Autowired 
	private ServizioService servizioService;
	@Autowired
	private ServizioValidator servizioValidator;

	@GetMapping(value="/admin/formNewServizio")
	public String formNewServizio(Model model) {
		model.addAttribute("servizio", new Servizio());
		return "admin/formNewServizio.html";
	}
	
	@GetMapping("/admin/manageServizio")
	public String manageServizio(Model model) {
		model.addAttribute("servizi", this.servizioService.findAll());
		return "admin/manageServizi.html";
	}
	
	@GetMapping(value="/admin/indexServizio")
	public String indexServizio() {
		return "admin/indexServizio.html";
	}
	
	@PostMapping("/admin/servizio")
	public String newServizio( @Valid @ModelAttribute("servizio") Servizio servizio,BindingResult bindingResult, Model model) {
		this.servizioValidator.validate(servizio, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.servizioService.save(servizio); 
			model.addAttribute("servizio", servizio);
			return "redirect:/servizio/" +servizio.getId();
		} else {
			return "admin/formNewServizio.html"; 
		}
	}

	@GetMapping("/servizio/{id}")
	public String getServizio(@PathVariable("id") Long id, Model model) {
		model.addAttribute("servizio", this.servizioService.findById(id));
		return "servizio.html";
	}

	@GetMapping("/servizio")
	public String getServizi(Model model) {
		model.addAttribute("servizi", this.servizioService.findAll());
		return "servizi.html";
	}
	
	@GetMapping("/admin/servizio/{id}")
    public String deleteServizio(@PathVariable("id") Long id, Model model) {
        Servizio servizio = servizioService.findById(id);
        if (servizio != null) {
            servizioService.deleteById(id);
            // Redirect alla pagina dell'indice dei servizi dopo la cancellazione
            return "redirect:/servizio";
        } else {
            // Nel caso in cui il servizio non esista
            model.addAttribute("messaggioErrore", "Servizio non trovato");
            return "admin/indexServizio.html";
            }
        }
}
