package com.agenda.Agenda.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agenda.Agenda.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long>{

	Optional<Login> findByEmail(String email);

}
