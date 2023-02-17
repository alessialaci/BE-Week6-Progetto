package it.alessialacitignola.epicode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.alessialacitignola.epicode.entities.Dispositivo;
import it.alessialacitignola.epicode.entities.Ruolo;
import it.alessialacitignola.epicode.entities.Utente;
import it.alessialacitignola.epicode.entities.enums.StatoDispositivo;
import it.alessialacitignola.epicode.entities.enums.TipoDispositivo;
import it.alessialacitignola.epicode.entities.enums.TipoRuolo;

@Configuration
public class Beans {

	@Bean
	@Scope("prototype")
	public Utente utente(String username, String nome, String cognome, String email, String password) {
		return Utente.builder()
				.username(username)
				.nome(nome)
				.cognome(cognome)
				.email(email)
				.password(password)
				.attivo(true)
				.build();
	}
	
	@Bean
	@Scope("prototype")
	public Ruolo ruolo(TipoRuolo tr) {
		return Ruolo.builder()
				.tipoRuolo(tr)
				.build();
	}
	
	@Bean
	@Scope("prototype")
	public Dispositivo dispositivo(StatoDispositivo sd, TipoDispositivo td) {
		return Dispositivo.builder()
				.stato(sd)
				.tipoDispositivo(td)
				.build();
	}
	
}
