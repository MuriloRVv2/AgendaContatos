package com.agenda.Agenda.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agenda.Agenda.model.Login;
import com.agenda.Agenda.repository.LoginRepository;

import org.springframework.ui.Model;

@Controller
public class LoginController {

	 @Autowired
	    private LoginRepository lr;

	 @PostMapping("/login")
	    public String login(@RequestParam String email,
	                        @RequestParam String senha,
	                        Model model) {

	        Optional<Login> usuarioOpt = lr.findByEmail(email);

	        if (usuarioOpt.isPresent()) {
	            Login login = usuarioOpt.get();

	            if (login.getSenha().equals(senha)) {
	                return "redirect:/paginaInicial"; // você precisa criar essa página
	            } else {
	                model.addAttribute("erro", "Senha incorreta.");
	                return "index";
	            }
	        } else {
	            model.addAttribute("erro", "Usuário não encontrado.");
	            return "index";
	        }
	    }
	 
}

	
