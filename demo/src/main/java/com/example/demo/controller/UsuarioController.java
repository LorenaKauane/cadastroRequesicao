package com.example.demo.controller;

import com.example.demo.Predicate.UsuarioSearchPredicate;
import com.example.demo.repositorios.UsuarioRepositorio;
import com.querydsl.core.types.dsl.BooleanExpression;
import javassist.NotFoundException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidade.Usuario;
import com.example.demo.service.UsuarioService;

import java.util.List;

@Controller
@RequestMapping(value="usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioRepositorio;
	@Autowired
	private UsuarioRepositorio repositorio;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin(origins="*")
	public String salvarUsuario(@RequestBody Usuario usuario) {  
		usuarioRepositorio.salvar(usuario);												//OK
		return "Salvo!";
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin(origins="*")
	public String editarUsuario(@RequestBody Usuario usuario) {  
		usuarioRepositorio.salvar(usuario);												//OK
		return "Salvo!";
	}
	

	@RequestMapping(value = "/{idUsuario}", method = RequestMethod.DELETE)
	@ResponseBody
	@CrossOrigin(origins="*")
	public String deletarUsuario(@PathVariable Integer idUsuario) {
		try {
			usuarioRepositorio.deletar(idUsuario);
		}catch (Exception ex){
			throw new IllegalArgumentException("O Usuario está vinculado a uma requesição. Delete a requesição primeiro");
		}//OK
		return "Deletado!";
	}
		
	@RequestMapping(value = "/{idUsuario}", method = RequestMethod.GET)
	@ResponseBody
	public Usuario buscarUsuario(@PathVariable Integer idUsuario) {    				//OK
		return usuarioRepositorio.buscarUsuario(idUsuario);
	}

	@RequestMapping( method = RequestMethod.GET)
	@ResponseBody
	public List<Usuario> listaUsuarios(@RequestParam(required = false, value = "search") final String search){

		if(StringUtils.isEmpty(search)){
			return (List<Usuario>) repositorio.findAll();
		}

		BooleanExpression expression = new UsuarioSearchPredicate(Usuario.class,search).getExpression();

		if(expression == null){
			return null;
		}
		System.out.println(expression);
		return (List<Usuario>) repositorio.findAll(expression);

	}

}
