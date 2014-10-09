package com.quartashow.jchampionship.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Classificacao {

	@Id @GeneratedValue
	private long id;
	
	@NotNull
	@ManyToOne
	private Time time;
	
	private int colocacao = 1; 
	
	private int pontos = 0;
	
	private int vitorias = 0;
	
	private int derrotas = 0;
	
	private int empates = 0;
	
	private int jogos = 0;
	
	@NotNull
	@ManyToOne
	private Grupo grupo; 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getVitorias() {
		return vitorias;
	}

	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public int getColocacao() {
		return colocacao;
	}

	public void setColocacao(int colocacao) {
		this.colocacao = colocacao;
	}

	public int getJogos() {
		return jogos;
	}

	public void setJogos(int jogos) {
		this.jogos = jogos;
	}

	@Override
	public String toString() {
		return "Classificacao [id=" + id + ", time=" + time + ", colocacao="
				+ colocacao + ", pontos=" + pontos + ", vitorias=" + vitorias
				+ ", derrotas=" + derrotas + ", empates=" + empates
				+ ", jogos=" + jogos + ", grupo=" + grupo + "]";
	}



	
}
