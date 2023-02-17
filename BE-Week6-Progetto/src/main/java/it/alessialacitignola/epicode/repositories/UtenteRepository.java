package it.alessialacitignola.epicode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alessialacitignola.epicode.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

}
