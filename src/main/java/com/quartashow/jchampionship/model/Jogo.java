package com.quartashow.jchampionship.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	private Harbito harbito;

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

	@Override
	public String toString() {
		return "Jogo [id=" + id + ", grupo=" + grupo + ", timeA=" + timeA
				+ ", resultadoA=" + resultadoA + ", timeB=" + timeB
				+ ", resultadoB=" + resultadoB + ", local=" + local
				+ ", harbito=" + harbito + "]";
	}
	
	
}