package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidade.Usuario;
import com.example.demo.entidade.Requesicao;
import com.example.demo.repositorios.RequesicaoRepositorio;

@Service
public class RequesicaoService {

	@Autowired
	private RequesicaoRepositorio repository;
	
	public Requesicao salvar(Requesicao requesicao) {
		return repository.save(requesicao);
	}
	
	public List<Requesicao> listarTodos(){
		return (List) repository.findAll();
	}
	
	public Requesicao buscarRequesicao(Integer id) {
		return repository.findOne(id);
	}
	
	public void deletar(Integer id) {
		repository.delete(id);
	}
	
	public List<Requesicao> buscarPorCliente(Usuario usuario) {
		return  repository.findByUsuario(usuario);
	}
	
	
}
