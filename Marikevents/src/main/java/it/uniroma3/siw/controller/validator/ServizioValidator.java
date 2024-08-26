package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.model.Servizio;
import it.uniroma3.siw.repository.ServizioRepository;

@Component
public class ServizioValidator implements Validator {
	@Autowired
	private ServizioRepository servizioRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Servizio servizio = (Servizio)o;
		if (servizio.getNome()!=null 
				&& servizioRepository.existsByNomeIgnoreCase(servizio.getNome())) {
			errors.reject("servizio.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Evento.class.equals(aClass);
	}
}