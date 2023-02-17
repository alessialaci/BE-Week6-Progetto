package it.alessialacitignola.epicode.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.alessialacitignola.epicode.entities.Ruolo;
import it.alessialacitignola.epicode.repositories.RuoloRepository;

@Service
public class RuoloService {
	
	@Autowired
	private RuoloRepository rr;
	
	public Ruolo saveRuolo(Ruolo r) {
		return rr.save(r);
	}
	
	public List<Ruolo> getAll() {
		return rr.findAll();
	}
	
	public Optional<Ruolo> getById(int id) {
		return rr.findById(id);
	}
	
	public void delete(Ruolo d) {
		rr.delete(d);
	}
	
	public Page<Ruolo> getAllInPage(Pageable pageable) {
		return rr.findAll(pageable);
	}

}
