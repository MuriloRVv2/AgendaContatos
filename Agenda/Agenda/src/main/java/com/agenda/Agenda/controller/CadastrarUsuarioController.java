package com.agenda.Agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agenda.Agenda.model.Login;
import com.agenda.Agenda.repository.LoginRepository;

@Controller
public class CadastrarUsuarioController {

	@Autowired
    private LoginRepository loginRepository;
	
	@GetMapping("/cadastrarUsuario")
	public String CadastrarUsuario() {
		return "/cadastrarUsuario";
	}
	
	@PostMapping("/cadastrarUsuario")
    public String processarCadastro(@RequestParam String nome,
                                   @RequestParam String email,
                                   @RequestParam String senha,
                                   @RequestParam String confirmarSenha,
                                   Model model) {
        if (!senha.equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem.");
            return "cadastrarUsuario";
        }

        if (loginRepository.findByEmail(email).isPresent()) {
            model.addAttribute("erro", "Email já cadastrado.");
            return "cadastrarUsuario";
        }

        Login novoLogin = new Login();
        novoLogin.setUsuario(nome);
        novoLogin.setEmail(email);
        novoLogin.setSenha(senha);

        loginRepository.save(novoLogin);

        model.addAttribute("sucesso", "Cadastro realizado com sucesso! Faça login.");
        return "index";
    }
	
}
