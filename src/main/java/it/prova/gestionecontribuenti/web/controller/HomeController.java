package it.prova.gestionecontribuenti.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping(value = {"gestionecontribuenti", "home"})
	public String loginMessage() {
		return "index";
	}
}
