package it.alessialacitignola.epicode.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.alessialacitignola.epicode.entities.Utente;
import it.alessialacitignola.epicode.repositories.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository ur;
	
	public Utente saveUtente(Utente u) {
		return ur.save(u);
	}

	public List<Utente> getAll() {
		return ur.findAll();
	}
	
	public Optional<Utente> getById(int id) {
		return ur.findById(id);
	}
	
	public void delete(Utente d) {
		ur.delete(d);
	}
	
	public Page<Utente> getAllInPage(Pageable pageable) {
		return ur.findAll(pageable);
	}
	
}
