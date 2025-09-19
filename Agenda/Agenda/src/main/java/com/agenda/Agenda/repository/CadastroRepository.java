package com.agenda.Agenda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agenda.Agenda.model.Cadastro;

@Repository
public interface CadastroRepository extends CrudRepository<Cadastro, Long>{

}
