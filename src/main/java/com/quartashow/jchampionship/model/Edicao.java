package com.quartashow.jchampionship.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Edicao {
	
	@Id @GeneratedValue
	private long id;
	
	private String descricao;
	
	@ManyToOne
	private Status status;
	
	@ManyToOne
	private Campeonato campeonato;
	
//	@OneToMany(mappedBy="edicao")
//	private List<Grupo> grupos;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Campeonato getCampeonato() {
		return campeonato;
	}
	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
//	public List<Grupo> getGrupos() {
//		return grupos;
//	}
//	public void setGrupos(List<Grupo> grupos) {
//		this.grupos = grupos;
//	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Edicao [id=" + id + ", descricao=" + descricao + ", status="
				+ status + ", campeonato=" + campeonato + "]";
	}

	
	
}