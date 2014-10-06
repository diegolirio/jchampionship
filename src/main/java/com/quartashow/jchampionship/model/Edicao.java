package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class Edicao {
	
	@Id @GeneratedValue
	private long id;
	
	@NotNull @Size(min=4, max=25)
	private String descricao;
	
	@ManyToOne
	private Status status;
	
	@NotNull
	@ManyToOne
	private Campeonato campeonato;
	
	@JsonBackReference
	@OneToMany(mappedBy="edicao")
	private List<Grupo> grupos;
	
	public Edicao() {}
	
	public Edicao(long id) {
		this.id = id;
	}
	
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
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
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
