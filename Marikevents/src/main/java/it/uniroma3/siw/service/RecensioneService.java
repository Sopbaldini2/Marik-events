package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import it.uniroma3.siw.model.Cliente;
//import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.repository.RecensioneRepository;

@Service
public class RecensioneService {
	@Autowired 
	private RecensioneRepository recensioneRepository;
	
	@Transactional
	public void save(Recensione recensione) {
		recensioneRepository.save(recensione);
	}

	@Transactional
	public Recensione findById(Long id) {
		return recensioneRepository.findById(id).get();
	}
	
	@Transactional
	 public void delete(Recensione recensione) {
	        recensioneRepository.delete(recensione);
	 }

	@Transactional
	public boolean existsById(Long id) {
		return recensioneRepository.existsById(id);
	}

	@Transactional
	public Iterable<Recensione> findAll() {
		return recensioneRepository.findAll();
	}

	/*@Transactional
	public boolean existsByClienteAndEvento(Cliente cliente, Evento evento) {
        return recensioneRepository.existsByClienteAndEvento(cliente, evento);
    }*/

	/*@Transactional
	public List<Recensione> findByCliente(Cliente cliente) {
        return recensioneRepository.findByCliente(cliente);
    }*/

	@Transactional
	public List<Recensione> findByEventoId(Long id) {
		return recensioneRepository.findByEventoId(id);
	}

	@Transactional
	public void deleteById(Long id) {
		recensioneRepository.deleteById(id);
	}
}