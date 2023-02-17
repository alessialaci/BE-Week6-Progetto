package it.alessialacitignola.epicode.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="utenti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Utente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private boolean attivo;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Dispositivo> dispositivi;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "utenti_ruoli",
		joinColumns = @JoinColumn(name = "utente_id"),
		inverseJoinColumns = @JoinColumn(name = "ruolo_id")
	)
	private Set<Ruolo> ruoli;
	
}
