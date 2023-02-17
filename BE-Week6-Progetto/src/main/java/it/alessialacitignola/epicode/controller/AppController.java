package it.alessialacitignola.epicode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {
	
	@PostMapping("login_success")
	public String login_success() {
		return "login_success";
	}
	
	@GetMapping("/")
	public String index() {
		return "Sei nella homepage";
	}

}
