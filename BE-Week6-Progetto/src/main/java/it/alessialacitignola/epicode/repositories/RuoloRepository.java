package it.alessialacitignola.epicode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alessialacitignola.epicode.entities.Ruolo;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, Integer> {

}
