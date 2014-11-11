package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class Campeonato {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(unique=true)
	private String descricao;
	
	@JsonBackReference
	@OneToMany(mappedBy="campeonato")
	private List<Edicao> edicoes;
	
	@JsonBackReference
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Usuario> usuarios;
	
	private String imgName = "/static/quartashow/img/trofeus/trophy1.png";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Edicao> getEdicoes() {
		return edicoes;
	}
	public void setEdicoes(List<Edicao> edicoes) {
		this.edicoes = edicoes;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	@Override
	public String toString() {
		return "Campeonato [id=" + id + ", descricao=" + descricao + "]";
	}

	
	
}
