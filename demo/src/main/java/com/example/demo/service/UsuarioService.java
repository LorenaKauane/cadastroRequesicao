package com.example.demo.service;

import java.util.List;

import com.example.demo.entidade.QUsuario;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entidade.Usuario;
import com.example.demo.repositorios.UsuarioRepositorio;

import javax.persistence.EntityManager;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepositorio repository;
	private EntityManager entityManager;
	 
	 public void salvar(Usuario usuario) {
		 repository.save(usuario);
	 }
	 
	 public void deletar(Integer id) {
		 repository.delete(id);
	 }
	 
	 public List<Usuario> listarTodos(){
		 return (List)repository.findAll();
	 }
	 
	 public Usuario buscarUsuario(Integer id) {
		 return repository.findOne(id);
	 }

	 public List<Usuario> lista (Predicate predicate){
		 return (List<Usuario>) repository.findAll(predicate);
	 }
	 
	 
}
