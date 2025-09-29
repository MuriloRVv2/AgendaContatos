package com.agenda.Agenda.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agenda.Agenda.model.Login;
import com.agenda.Agenda.repository.LoginRepository;

@Controller
public class EsquecerSenhaController {

	
	@Autowired
    private LoginRepository loginRepository;
	
	@GetMapping("/esquecerSenha")
    public String esquecerSenha() {
        return "esquecerSenha"; // seu template para esquecer senha
    }

    // Processar recuperação de senha
    @PostMapping("/esquecerSenha")
    public String processarEsquecerSenha(@RequestParam String email,
                                         Model model) {

        Optional<Login> usuarioOpt = loginRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            // Aqui, idealmente, você enviaria um e-mail com o link de recuperação
            // Mas para simplicidade, vamos mostrar mensagem
            model.addAttribute("sucesso", "Email de recuperação enviado para: " + email);
        } else {
            model.addAttribute("erro", "Email não encontrado.");
        }

        return "esquecerSenha";
    }
}
	
