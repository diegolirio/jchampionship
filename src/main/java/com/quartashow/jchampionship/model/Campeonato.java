package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class Campeonato {
	
	@Id @GeneratedValue
	private int id;
	
	private String descricao;
	
	@JsonBackReference
	@OneToMany(mappedBy="campeonato")
	private List<Edicao> edicoes;
	
	//private String dono;//usuario
	
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
	@Override
	public String toString() {
		return "Campeonato [id=" + id + ", descricao=" + descricao + "]";
	}

	
	
}
