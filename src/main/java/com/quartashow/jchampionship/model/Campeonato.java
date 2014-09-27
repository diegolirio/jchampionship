package com.quartashow.jchampionship.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Campeonato {
	
	@Id @GeneratedValue
	private int id;
	
	private String descricao;
	
	//@OneToMany(mappedBy="campeonato")
	//private List<Edicao> edicoes;
	
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
//	public List<Edicao> getEdicoes() {
//		return edicoes;
//	}
//	public void setEdicoes(List<Edicao> edicoes) {
//		this.edicoes = edicoes;
//	}
	
	
	

}
