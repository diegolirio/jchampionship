package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class Time {

	@Id @GeneratedValue
	private long id;

	@NotNull @Size(min=3, max=25)
	@Column(unique=true)
	private String nome;

	@JsonBackReference
	@ManyToMany(fetch=FetchType.EAGER)
	//@OrderBy(value="nome")
	private List<Jogador> jogadores;
	
	public Time() {}
	
	public Time(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

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

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	@Override
	public String toString() {
		return "Time [id=" + id + ", nome=" + nome + "]";
	}
	
	
}
