package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class JogadorEscalado {

	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	private Escalacao escalacao;
	
	@ManyToOne
	private Jogador jogador;
	
	@ManyToOne
	private Time time;

	//@ManyToMany(fetch=FetchType.EAGER)
	//private List<Evento> eventos;

	@OneToMany(mappedBy="jogadorEscalado", fetch=FetchType.EAGER)
	private List<CollectionEventos> eventos;
	
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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
	public List<CollectionEventos> getEventos() {
		return eventos;
	}

	public void setEventos(List<CollectionEventos> eventos) {
		this.eventos = eventos;
	}

	@Override
	public String toString() {
		return "JogadorEscalado [id=" + id + ", escalacao=" + escalacao
				+ ", jogador=" + jogador + ", time=" + time + "]";
	}
	
	
	
}
