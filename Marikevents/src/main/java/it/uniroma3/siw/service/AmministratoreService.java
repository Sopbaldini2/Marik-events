package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Amministratore;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.AmministratoreRepository;
import it.uniroma3.siw.repository.CredentialsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The UserService handles logic for Users.
 */
@Service
public class AmministratoreService {

    @Autowired
    protected AmministratoreRepository marikRepository;
    @Autowired
    protected CredentialsRepository credentialsRepository;

    /**
     * This method retrieves a User from the DB based on its ID.
     * @param id the id of the User to retrieve from the DB
     * @return the retrieved User, or null if no User with the passed ID could be found in the DB
     */
    @Transactional
    public Amministratore getUser(Long id) {
        Optional<Amministratore> result = this.marikRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a User in the DB.
     * @param user the User to save into the DB
     * @return the saved User
     * @throws DataIntegrityViolationException if a User with the same username
     *                              as the passed User already exists in the DB
     */
    @Transactional
    public Amministratore saveUser(Amministratore user) {
        return this.marikRepository.save(user);
    }

    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<Amministratore> getAllUsers() {
        List<Amministratore> result = new ArrayList<>();
        Iterable<Amministratore> iterable = this.marikRepository.findAll();
        for(Amministratore user : iterable)
            result.add(user);
        return result;
    }

    @Transactional
	public Iterable<Amministratore> findAll() {
		return marikRepository.findAll();
	}

    @Transactional
	public Amministratore findClienteByUsername(String clienteNome) {
		Credentials credentials = credentialsRepository.findClienteByUsername(clienteNome);
        if (credentials != null) {
            return credentials.getMarik(); // Assumi che Credentials abbia un campo cliente
        }
        return null;
	}

}
