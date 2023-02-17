package it.alessialacitignola.epicode;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.alessialacitignola.epicode.config.Beans;
import it.alessialacitignola.epicode.entities.Dispositivo;
import it.alessialacitignola.epicode.entities.Ruolo;
import it.alessialacitignola.epicode.entities.Utente;
import it.alessialacitignola.epicode.entities.enums.StatoDispositivo;
import it.alessialacitignola.epicode.entities.enums.TipoDispositivo;
import it.alessialacitignola.epicode.entities.enums.TipoRuolo;
import it.alessialacitignola.epicode.services.DispositivoService;
import it.alessialacitignola.epicode.services.RuoloService;
import it.alessialacitignola.epicode.services.UtenteService;


@SpringBootApplication
public class BeWeek6ProgettoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BeWeek6ProgettoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//popolaDb();
	}
	
	@Autowired
	private UtenteService us;
	
	@Autowired
	private RuoloService rs;
	
	@Autowired
	private DispositivoService ds;
	
	public void popolaDb() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		
		Ruolo r1 = (Ruolo) ctx.getBean("ruolo", TipoRuolo.ROLE_ADMIN);
		Ruolo r2 = (Ruolo) ctx.getBean("ruolo", TipoRuolo.ROLE_USER);
		Dispositivo d1 = (Dispositivo) ctx.getBean("dispositivo", StatoDispositivo.ASSEGNATO, TipoDispositivo.LAPTOP);
		Dispositivo d2 = (Dispositivo) ctx.getBean("dispositivo", StatoDispositivo.IN_MANUTENZIONE, TipoDispositivo.SMARTPHONE);
		Dispositivo d3 = (Dispositivo) ctx.getBean("dispositivo", StatoDispositivo.ASSEGNATO, TipoDispositivo.TABLET);		
		Utente u1 = (Utente) ctx.getBean("utente", "mariorossi", "Mario", "Rossi", "m@r.it", "pwm");
		Utente u2 = (Utente) ctx.getBean("utente", "luigiverdi", "Luigi", "Verdi", "l@v.it", "pwl");

		
		u1.setRuoli(new HashSet<>() {{
			add(r1);
		}});
		
		u1.setDispositivi(new HashSet<>() {{
			add(d1);
			add(d2);
		}});
		
		u2.setRuoli(new HashSet<>() {{
			add(r2);
		}});
		
		u2.setDispositivi(new HashSet<>() {{
			add(d3);
		}});
		
		rs.saveRuolo(r1);
		rs.saveRuolo(r2);
		
		ds.saveDispositivo(d1);
		ds.saveDispositivo(d2);
		ds.saveDispositivo(d3);
		
		us.saveUtente(u1);
		us.saveUtente(u2);
		
		((AnnotationConfigApplicationContext)ctx).close();
	}

}
