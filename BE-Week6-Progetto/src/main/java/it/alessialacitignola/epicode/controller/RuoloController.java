package it.alessialacitignola.epicode.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.alessialacitignola.epicode.entities.Ruolo;
import it.alessialacitignola.epicode.services.RuoloService;

@RestController
@RequestMapping("/")
public class RuoloController {
	
	@Autowired
	private RuoloService rs;
	
	@GetMapping("ruoli")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Ruolo>> getDispositivi() {
		List<Ruolo> ruoli = rs.getAll();
		
		if(ruoli.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(ruoli, HttpStatus.CREATED);
	}
	
	@GetMapping("ruoli/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getById(@PathVariable Integer id) {
		Optional<Ruolo> ruoloObj = rs.getById(id);
		
		ResponseEntity<Object> check = checkExists(ruoloObj);
		if(check != null) return check;
		
		return new ResponseEntity<>(ruoloObj.get(), HttpStatus.OK);
	}
	
	@GetMapping("ruoli_page")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getAll_page(Pageable pageable) {
		Page<Ruolo> ruoli = rs.getAllInPage(pageable);
		
		if(ruoli.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(ruoli, HttpStatus.OK);
	}
	
	@PostMapping("ruoli")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> create(@RequestBody Ruolo r) {
		Ruolo ruolo = rs.saveRuolo(r);
		
		return new ResponseEntity<Object>(ruolo, HttpStatus.CREATED);
	}
	
	@PutMapping("ruoli/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Ruolo _ruolo) {
		Optional<Ruolo> ruoloObj = rs.getById(id);
		
		ResponseEntity<Object> check = checkExists(ruoloObj);
		if(check != null) return check;
		
		Ruolo ruolo = ruoloObj.get();
		ruolo.setTipoRuolo(_ruolo.getTipoRuolo());
		rs.saveRuolo(ruolo);
		
		return new ResponseEntity<Object>(ruolo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("ruoli/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		Optional<Ruolo> ruoloObj = rs.getById(id);
		
		ResponseEntity<Object> check = checkExists(ruoloObj);
		if(check != null) return check;
		
		rs.delete(ruoloObj.get());
		return new ResponseEntity<>(String.format("Il Ruolo con id %d è stato eliminato!", id), HttpStatus.OK);
	}
	
	private ResponseEntity<Object> checkExists(Optional<Ruolo> obj) {
		if( !obj.isPresent() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return null;
	}
	
}
