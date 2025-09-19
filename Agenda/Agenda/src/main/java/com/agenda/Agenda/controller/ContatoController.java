package com.agenda.Agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agenda.Agenda.model.Cadastro;
import com.agenda.Agenda.repository.CadastroRepository;


@Controller
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private CadastroRepository cadastroRepository;

    // Listar contatos
    @GetMapping
    public String listarContatos(Model model) {
        Iterable<Cadastro> contatos = cadastroRepository.findAll();
        model.addAttribute("contatos", contatos);
        return "contato"; // nome do template HTML que você vai criar
    }
    
    @DeleteMapping("/excluir/{id}")
	public String excluirContato(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		try {
			Cadastro cadastro= cadastroRepository.findById(id).orElse(null);
			if(cadastro != null) {
				cadastroRepository.delete(cadastro);
				redirectAttributes.addFlashAttribute("mensagem", "Contato excluído com sucesso");
			} else {
				redirectAttributes.addFlashAttribute("erro", "Contato não encontrado");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("erro", "Erro ao excluir contato: " + e.getMessage());
		}
		return "redirect:/";
	}
    
    @PostMapping("/excluir/{id}")
	public String excluirContatoPost(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		return excluirContato(id, redirectAttributes);
	}
}
