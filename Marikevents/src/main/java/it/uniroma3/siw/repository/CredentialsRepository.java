package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.*;

public interface CredentialsRepository extends CrudRepository<Credentials,Long> {

	public Optional<Credentials> findByUsername(String username);
	Credentials findClienteByUsername(String username);

}
