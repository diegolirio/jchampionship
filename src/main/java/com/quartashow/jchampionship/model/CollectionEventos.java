package com.quartashow.jchampionship.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CollectionEventos {
	
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	private JogadorEscalado jogadorEscalado;
	
	@ManyToOne
	private Evento evento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JogadorEscalado getJogadorEscalado() {
		return jogadorEscalado;
	}

	public void setJogadorEscalado(JogadorEscalado jogadorEscalado) {
		this.jogadorEscalado = jogadorEscalado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	

}
