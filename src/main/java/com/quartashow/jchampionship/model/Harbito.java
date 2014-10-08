package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Harbito {

	@Id @GeneratedValue
	private long id;
	
	@NotNull @Size(min=3, max=50)
	private String nome;

	@OneToMany(mappedBy="harbito")
	private List<Jogo> jogos;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	@Override
	public String toString() {
		return "Harbito [id=" + id + ", nome=" + nome + "]";
	}
	
	
}
