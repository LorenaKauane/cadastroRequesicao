package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entidade.Usuario;
import com.example.demo.entidade.Requesicao;

public interface RequesicaoRepositorio extends CrudRepository<Requesicao , Integer> ,QueryDslPredicateExecutor<Requesicao> {
	List<Requesicao> findByUsuario(Usuario usuario);
}
