package com.example.demo.repositorios;

import java.util.List;


import com.example.demo.entidade.QUsuario;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entidade.Usuario;
import com.example.demo.entidade.Requesicao;

public interface UsuarioRepositorio extends CrudRepository<Usuario , Integer>,
        QueryDslPredicateExecutor<Usuario> {


}
