package it.alessialacitignola.epicode.config;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import it.alessialacitignola.epicode.entities.Ruolo;
import it.alessialacitignola.epicode.entities.Utente;
import it.alessialacitignola.epicode.services.UtenteService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UtenteService us;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/")
				.permitAll()
			.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.successForwardUrl("/login_success")
			.and()
			.logout()
			.and()
			.csrf()
				.disable();
	}
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		Optional<Utente> authUserObj = us.getById(1);
		Utente authUser = authUserObj.get();
		String ruolo  = "USER";
		Set<Ruolo> ruoli = authUser.getRuoli();
		for(Ruolo r : ruoli) {
			if(r.getTipoRuolo().toString().contains("ADMIN")) {
				ruolo = "ADMIN";
				break;
			}
		}
		
		auth.inMemoryAuthentication()
			.withUser(authUser.getUsername()).password(passwordEncoder().encode(authUser.getPassword()))
			.roles(ruolo);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
