package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.*;

public interface DipendenteRepository extends CrudRepository<Dipendente,Long>{
	boolean existsByNomeIgnoreCaseAndCognomeIgnoreCase(String nome, String cognome);

}
