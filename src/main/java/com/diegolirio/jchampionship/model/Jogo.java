package com.diegolirio.jchampionship.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Jogo {
	
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	private Harbito harbito;
	
	private Calendar dataHora;
	
	@OneToOne
	private Time timeA;
	
	private int resultadoA;
	
	private int resultadoB;
	
	@OneToOne
	private Time timeB;
	
	@OneToMany
	private Grupo grupo;
	
	@ManyToOne
	private Local local;
	
	@OneToOne
	private Escalacao escalacao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Harbito getHarbito() {
		return harbito;
	}
	public void setHarbito(Harbito harbito) {
		this.harbito = harbito;
	}
	public Calendar getDataHora() {
		return dataHora;
	}
	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}
	public Time getTimeA() {
		return timeA;
	}
	public void setTimeA(Time timeA) {
		this.timeA = timeA;
	}
	public Time getTimeB() {
		return timeB;
	}
	public void setTimeB(Time timeB) {
		this.timeB = timeB;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public int getResultadoA() {
		return resultadoA;
	}
	public void setResultadoA(int resultadoA) {
		this.resultadoA = resultadoA;
	}
	public int getResultadoB() {
		return resultadoB;
	}
	public void setResultadoB(int resultadoB) {
		this.resultadoB = resultadoB;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public Escalacao getEscalacao() {
		return escalacao;
	}
	public void setEscalacao(Escalacao escalacao) {
		this.escalacao = escalacao;
	}

	
	

}
