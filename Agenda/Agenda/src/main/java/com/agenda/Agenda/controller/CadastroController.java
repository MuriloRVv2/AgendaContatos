package com.agenda.Agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.agenda.Agenda.model.Cadastro;
import com.agenda.Agenda.repository.CadastroRepository;

@Controller
public class CadastroController {
	
	@Autowired
	private CadastroRepository cr;
	
	@GetMapping("/cadastro")
	public String cadastro() {
		return "cadastro";
	}
	
	@PostMapping(value = "/cadastro")
	public String cadastro(Cadastro cadastro) {
		cr.save(cadastro);
		return "redirect:/";
	}
	
	@RequestMapping("/cadastro")
	public ModelAndView listasolicitacao() {
		ModelAndView mv = new ModelAndView("/contato");
		Iterable<Cadastro> cadastro = cr.findAll();
		mv.addObject("cadastro", cadastro);
		return mv;
	}
}
