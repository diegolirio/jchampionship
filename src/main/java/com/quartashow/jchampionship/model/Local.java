package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Local {

	@Id @GeneratedValue
	private long id;
	
	@NotNull @Size(min=2, max=20)
	@Column(length=20)
	private String descricao;
	
	@OneToMany(mappedBy="local")
	private List<Jogo> jogos;

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

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	@Override
	public String toString() {
		return "Local [id=" + id + ", descricao=" + descricao + "]";
	}
	
	
	
}
