package com.diegolirio.jchampionship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class JogadorEscalado {

	@Id @GeneratedValue
	private long id;
	
	@OneToMany
	private Escalacao escalacao;
	
	@OneToOne
	private Jogador jogador;
	
	@ManyToMany(mappedBy="jogadoresEscalados")
	private List<Evento> eventos;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Escalacao getEscalacao() {
		return escalacao;
	}
	public void setEscalacao(Escalacao escalacao) {
		this.escalacao = escalacao;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public List<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
}
