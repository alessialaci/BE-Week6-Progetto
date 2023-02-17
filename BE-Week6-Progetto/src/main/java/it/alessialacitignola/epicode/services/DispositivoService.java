package it.alessialacitignola.epicode.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.alessialacitignola.epicode.entities.Dispositivo;
import it.alessialacitignola.epicode.repositories.DispositivoRepository;

@Service
public class DispositivoService {

	@Autowired
	private DispositivoRepository dr;
	
	public Dispositivo saveDispositivo(Dispositivo d) {
		return dr.save(d);
	}
	
	public List<Dispositivo> getAll() {
		return dr.findAll();
	}
	
	public Optional<Dispositivo> getById(int id) {
		return dr.findById(id);
	}
	
	public void delete(Dispositivo d) {
		dr.delete(d);
	}
	
	public Page<Dispositivo> getAllInPage(Pageable pageable) {
		return dr.findAll(pageable);
	}
	
}
