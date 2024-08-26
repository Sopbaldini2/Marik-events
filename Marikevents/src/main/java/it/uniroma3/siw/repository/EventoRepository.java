package it.uniroma3.siw.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Evento;

public interface EventoRepository extends CrudRepository<Evento,Long>{
	
	public List<Evento> findByNome(String nome);
	
	boolean existsByNomeIgnoreCaseAndData(String nome, LocalDate data);
	
	@Modifying
    @Query(value = "delete from recensione WHERE evento_id = :eventoId", nativeQuery=true)
    void deleteReferencesInRecensione(@Param("eventoId") Long eventoId);

	@Query("select e from Evento e ORDER BY e.nome")
	public List<Evento> findAllSortedByNome();

}