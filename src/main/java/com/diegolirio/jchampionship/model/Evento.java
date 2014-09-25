package com.diegolirio.jchampionship.model;

import java.util.List;

public class Evento {

	private long id;
	private String descricao;
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
