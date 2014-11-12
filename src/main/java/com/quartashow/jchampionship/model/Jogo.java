package com.quartashow.jchampionship.model;

import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Jogo {

	@Id @GeneratedValue
	private long id;
	  
	@NotNull
	@ManyToOne
	private Grupo grupo;
	
	@NotNull
	@ManyToOne
	private Time timeA; 
	
	private int resultadoA = 0;
	
	@NotNull
	@ManyToOne
	private Time timeB;
	
	private int resultadoB = 0;
	
	@NotNull
	@ManyToOne
	private Local local;
	
	@NotNull
	@ManyToOne
	private Harbito harbito;
	
	@ManyToOne
	private Status status;
	
	private int rodada = -1;

	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dataHora;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Time getTimeA() {
		return timeA;
	}

	public void setTimeA(Time timeA) {
		this.timeA = timeA;
	}

	public int getResultadoA() {
		return resultadoA;
	}

	public void setResultadoA(int resultadoA) {
		this.resultadoA = resultadoA;
	}

	public Time getTimeB() {
		return timeB;
	}

	public void setTimeB(Time timeB) {
		this.timeB = timeB;
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

	public Harbito getHarbito() {
		return harbito;
	}

	public void setHarbito(Harbito harbito) {
		this.harbito = harbito;
	}
	
	public Status getStatus() {
		return status;
	}

	public java.util.Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(java.util.Date dataHora) {
		this.dataHora = dataHora;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public int getRodada() {
		return rodada;
	}

	public void setRodada(int rodada) {
		this.rodada = rodada;
	}

	public String getDataHoraStrEN() {
		return new SimpleDateFormat("yyyy/MM/dd").format(this.dataHora);
	}
	
	public String getDataHoraStrBR() {
		return new SimpleDateFormat("dd/MM/yyyy").format(this.dataHora);
	}	
	
	@Override
	public String toString() {
		return "Jogo [id=" + id + ", grupo=" + grupo + ", timeA=" + timeA
				+ ", resultadoA=" + resultadoA + ", timeB=" + timeB
				+ ", resultadoB=" + resultadoB + ", local=" + local
				+ ", harbito=" + harbito + "]";
	}
	
	
}
