package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Dipendente;
import it.uniroma3.siw.repository.DipendenteRepository;


@Component
public class DipendenteValidator implements Validator {
	@Autowired
	private DipendenteRepository DipendenteRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Dipendente Dipendente = (Dipendente)o;
		if (Dipendente.getNome()!=null && Dipendente.getCognome()!=null 
				&& DipendenteRepository.existsByNomeIgnoreCaseAndCognomeIgnoreCase(Dipendente.getNome(), Dipendente.getCognome())) {
			errors.reject("dipendente.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Dipendente.class.equals(aClass);
	}
}