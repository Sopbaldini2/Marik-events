package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Recensione;

public interface RecensioneRepository extends CrudRepository<Recensione,Long>{
	
	public boolean existsById(Long id);
	public List<Recensione> findByEventoId(Long id);

}
