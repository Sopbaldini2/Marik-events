package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Dipendente;
import it.uniroma3.siw.repository.DipendenteRepository;

@Service
public class DipendenteService {

	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@Transactional
	public void save(Dipendente dipendente) {
		dipendenteRepository.save(dipendente);
	}

	@Transactional
	public Dipendente findById(Long id) {
		return dipendenteRepository.findById(id).get();
	}

	@Transactional
	public Iterable<Dipendente> findAll() {
		return dipendenteRepository.findAll();
	}


	@Transactional
	public void delete(Dipendente dipendente) {
		dipendenteRepository.delete(dipendente);
	}
	 

}
