package com.agenda.Agenda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaInicialController {

	@GetMapping("/paginaInicial")
	public String PaginaInicial() {
		return "/paginaInicial";
	}
	
}
