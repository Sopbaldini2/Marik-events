package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Transactional
	public Evento findById(Long id) {
		return eventoRepository.findById(id).get();
	}

	@Transactional
	public Iterable<Evento> findAll() {
		return eventoRepository.findAll();
	}

	@Transactional
	public void save(Evento evento) {
		// TODO Auto-generated method stub
		eventoRepository.save(evento);
		
	}

	@Transactional
	public List<Evento> findByNome(String nome) {
		return eventoRepository.findByNome(nome);
	}

	@Transactional
	public void deleteById(Long id) {
		Evento evento= findById(id);
		eventoRepository.deleteReferencesInRecensione(id);
		eventoRepository.delete(evento);
	}

	public List<Evento> findAllSortedByNome() {
		return eventoRepository.findAllSortedByNome();
	}


}