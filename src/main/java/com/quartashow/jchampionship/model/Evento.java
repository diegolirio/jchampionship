package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Evento {

	@Id @GeneratedValue
	private long id;
	
	private String descricao;
	
	@ManyToMany(mappedBy="eventos")
	private List<JogadorEscalado> jogadoresEscalados;

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

	public List<JogadorEscalado> getJogadoresEscalados() {
		return jogadoresEscalados;
	}

	public void setJogadoresEscalados(List<JogadorEscalado> jogadoresEscalados) {
		this.jogadoresEscalados = jogadoresEscalados;
	}
	
	
	
}
