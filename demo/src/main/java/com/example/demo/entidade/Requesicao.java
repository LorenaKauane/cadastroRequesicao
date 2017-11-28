package com.example.demo.entidade;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import javassist.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="requesicao")
public class Requesicao {
	
	
	@Id
	@GeneratedValue
	private Integer idRequesicao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	//@Temporal(value = TemporalType.DATE)//"dd-MM-yyyy
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@DateTimeFormat (pattern = "dd/MM/yyyy")
	private Date data;
	
	private String tipoRequesicao;
	private String observacaoRequesicao;
	
	public Requesicao() {
		super();
		
	}
	public Integer getIdRequesicao() {
		return idRequesicao;
	}
	public void setIdRequesicao(Integer idRequesicao) {
		this.idRequesicao = idRequesicao;
	}

	public String getTipoRequesicao() {
		return tipoRequesicao;
	}
	public void setTipoRequesicao(String tipoRequesicao) {
		this.tipoRequesicao = tipoRequesicao;
	}
	public String getObservacaoRequesicao() {
		return observacaoRequesicao;
	}
	public void setObservacaoRequesicao(String observacaoRequesicao) {
		this.observacaoRequesicao = observacaoRequesicao;
	}
	public Date getData() {
		return data;
	}
	@SuppressWarnings("deprecation")
	public void setData(Date data) {
		this.data = data;
	}	
	public Usuario getUsuario() {
		return usuario;
	}	
	@Override
	public String toString() {
		return "Requesicao [idRequesicao=" + idRequesicao + ", tipoRequesicao="
				+ tipoRequesicao + ", observacaoRequesicao=" + observacaoRequesicao + ", data=" + data + "]";
	}
	
}
