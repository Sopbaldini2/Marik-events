package it.uniroma3.siw.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import it.uniroma3.siw.model.Cliente;
import it.uniroma3.siw.model.Evento;
//import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.model.Recensione;
//import it.uniroma3.siw.service.ClienteService;
import it.uniroma3.siw.service.EventoService;
import it.uniroma3.siw.service.RecensioneService;

@Controller
public class RecensioneController {
	@Autowired
	private RecensioneService recensioneService;
	/*@Autowired
	private ClienteService clienteService;*/
	@Autowired
	private EventoService eventoService;
	
	
	@GetMapping(value="/user/indexRecensione")
	public String indexRecensione() {
		return "user/indexRecensione.html";
	}
	
	@GetMapping("/admin/manageRecensione")
	public String manageRecensione(Model model) {
		model.addAttribute("recensioni", this.recensioneService.findAll());
		return "admin/manageRecensione.html";
	}
	
	@GetMapping("/recensione")
    public String getRecensioni(Model model) {
        List<Recensione> recensioni = (List<Recensione>) recensioneService.findAll();
        model.addAttribute("recensioni", recensioni);
        return "recensioni";
    }
	
	@GetMapping("/admin/recensione/{id}")
    public String deleteRecensione(@PathVariable("id") Long id, Model model) {
        Recensione recensione = recensioneService.findById(id);
        if (recensione != null) {
            recensioneService.delete(recensione);
            /* Redirect alla pagina dell'indice delle recensioni dopo la cancellazione*/
            return "redirect:/recensione";
        } else {
            // Nel caso in cui la recensione non esista
            model.addAttribute("messaggioErrore", "Recensione non trovato");
            return "user/indexRecensione.html";
            }
        }
	

	
	/*cliente*/
	/*@GetMapping("/cliente/formNewRecensione")
	public String formNewRecensione(@RequestParam("id") Long eventoId, Model model, Principal principal) {
		if (principal != null) {
	        String clienteNome = principal.getName(); // Ottieni il nome del cliente autenticato
	        Cliente cliente = clienteService.findClienteByUsername(clienteNome); // Recupera l'oggetto cliente completo
	        if (cliente != null) {
	            model.addAttribute("clienteNome", cliente.getNome()); // Aggiungi il nome del cliente al modello
	            model.addAttribute("clienteCognome", cliente.getCognome()); // Aggiungi il cognome del cliente al modello
	        }
	    }
	    model.addAttribute("recensione", new Recensione());
	    model.addAttribute("eventoId", eventoId);
	    return "cliente/formNewRecensione";
	}
	
	
	@PostMapping("/cliente/recensione")
	public String newRecensione(@ModelAttribute("recensione") Recensione recensione,
	                            @RequestParam("eventoId") Long eventoId,
	                            Principal principal, Model model) {
		
		String clienteNome = principal.getName();
		Cliente cliente = clienteService.findClienteByUsername(clienteNome);
		
		if (cliente == null) {
	        model.addAttribute("messaggioErrore", "Cliente non trovato");
	        return "cliente/formNewRecensione";
	    }
	    
	    recensione.setCliente(cliente);
	    
		Evento evento = eventoService.findById(eventoId);
	    if (evento != null) {
	        recensione.setEvento(evento);
	        
	        if (!recensioneService.existsByClienteAndEvento(recensione.getCliente(), evento)) {
	            recensioneService.save(recensione);
	            model.addAttribute("recensione", recensione);
	            return "redirect:/cliente/evento/" + eventoId;
	        } else {
	            model.addAttribute("messaggioErrore", "Questa recensione esiste già");
	            model.addAttribute("recensioni", recensioneService.findAll());
	            return "cliente/formNewRecensione"; 
	        }
	    } else {
	        // Gestione caso in cui l'evento non esiste
	        return "redirect:/cliente/evento";
	    }
	}*/
}








