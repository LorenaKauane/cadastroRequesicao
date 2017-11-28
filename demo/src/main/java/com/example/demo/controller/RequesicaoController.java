package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.demo.Predicate.RequesicaoPredicate;
import com.example.demo.Predicate.UsuarioSearchPredicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.entidade.Usuario;
import com.example.demo.entidade.Requesicao;
import com.example.demo.repositorios.UsuarioRepositorio;
import com.example.demo.repositorios.RequesicaoRepositorio;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.RequesicaoService;

import ch.qos.logback.core.net.server.Client;
import javassist.NotFoundException;

@Controller
@RequestMapping(value="requesicao")
public class RequesicaoController {

	@Autowired
	private UsuarioService usuarioRepositorio;
	@Autowired
	private RequesicaoRepositorio repositorio;
	@Autowired
	private RequesicaoService requesicaoRepositorio;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin(origins="*")
	public void salvarRequesicao(@RequestBody Requesicao requesicao) throws NotFoundException {
		if(requesicao.getData().getDate() == Calendar.getInstance().getTime().getDate()-1) {
			throw new IllegalArgumentException("A data tem que ser feita com antecedencia de 24hrs");
		}else {
			requesicaoRepositorio.salvar(requesicao);
		}
	}
	
	@RequestMapping(value = "/{idRequisicao}", method = RequestMethod.DELETE)
	@ResponseBody
	@CrossOrigin(origins="*")
	public String deletarRequesicao(@PathVariable Integer idRequisicao) {    
		requesicaoRepositorio.deletar(idRequisicao);											//OK
		return "Deletado!";
	}
	
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin(origins="*")
	public Requesicao editarRequesicao(@RequestBody Requesicao requesicao) {
		if(requesicao.getData().getDate() == Calendar.getInstance().getTime().getDate()-1) {
			throw new IllegalArgumentException("A data tem que ser feita com antecedencia de 24hrs");
		}else {
			return requesicaoRepositorio.salvar(requesicao);
		}
	}

	@RequestMapping(  method = RequestMethod.GET)
	@ResponseBody
	public List<Requesicao> listarRequesicao(@RequestParam(required = false, value = "search") final String search) {

		if(StringUtils.isEmpty(search)){
			return (List<Requesicao>) repositorio.findAll();
		}

		BooleanExpression expression = new RequesicaoPredicate<>(Requesicao.class,search).getExpression();

		if(expression == null){
			return null;
		}
		System.out.println(expression);
		return (List<Requesicao>) repositorio.findAll(expression);
	}
	
	
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Requesicao> buscarRequesicaoUsuario(@PathVariable("id") Integer id) {	
		Usuario findOne = usuarioRepositorio.buscarUsuario(id);
		return (List<Requesicao>) requesicaoRepositorio.buscarPorCliente(findOne); 						//ok
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Requesicao buscarRequesicao(@PathVariable("id") Integer id) {	
		return  requesicaoRepositorio.buscarRequesicao(id);							//OK				
	}
	

	
}
