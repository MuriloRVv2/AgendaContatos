package com.agenda.Agenda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.agenda.Agenda.model.Cadastro;
import com.agenda.Agenda.repository.CadastroRepository;

@Controller
@RequestMapping("/atualizar")
public class AtualizarController {

    @Autowired
    private CadastroRepository cadastroRepository;

    // Listar contatos para atualizar
    @GetMapping
    public String listarContatos(Model model) {
        Iterable<Cadastro> contatos = cadastroRepository.findAll();
        model.addAttribute("contatos", contatos);
        return "atualizar";
    }

    // Mostrar formulário para editar contato
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable ("id") Long id, Model model) {
        Optional<Cadastro> contatoOpt = cadastroRepository.findById(id);
        if (contatoOpt.isPresent()) {
            model.addAttribute("contato", contatoOpt.get());
            return "editar-contato"; // template para formulário de edição
        } else {
            return "redirect:/atualizar"; // contato não encontrado, volta para lista
        }
    }

    // Processar atualização do contato
    @PostMapping("/editar/{id}")
    public String atualizarContato(@PathVariable ("id") Long id, @ModelAttribute Cadastro contatoAtualizado) {
        Optional<Cadastro> contatoOpt = cadastroRepository.findById(id);
        if (contatoOpt.isPresent()) {
            Cadastro contato = contatoOpt.get();
            // Atualiza somente os campos que vieram no formulário (não obrigatório alterar todos)
            if (contatoAtualizado.getNome() != null && !contatoAtualizado.getNome().isEmpty()) {
                contato.setNome(contatoAtualizado.getNome());
            }
            if (contatoAtualizado.getEmail() != null && !contatoAtualizado.getEmail().isEmpty()) {
                contato.setEmail(contatoAtualizado.getEmail());
            }
            if (contatoAtualizado.getTelefone() != null && !contatoAtualizado.getTelefone().isEmpty()) {
                contato.setTelefone(contatoAtualizado.getTelefone());
            }
            if (contatoAtualizado.getEndereco() != null && !contatoAtualizado.getEndereco().isEmpty()) {
                contato.setEndereco(contatoAtualizado.getEndereco());
            }
            cadastroRepository.save(contato);
        }
        return "redirect:/atualizar";
    }
}
