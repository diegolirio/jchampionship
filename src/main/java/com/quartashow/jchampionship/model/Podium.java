package com.quartashow.jchampionship.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Podium {

	@Id @GeneratedValue
	private long id;
	
	@NotNull
	@ManyToOne
	private Edicao edicao;
	
	
	@ManyToOne
	private Time campeao;
	private boolean campeaoDefinido = false;
	
	@ManyToOne
	private Time viceCampeao;
	private boolean viceCampeaoDefinido = false;
	
	@OneToOne
	private Time terceiroColocado;
	private boolean terceiroColocadoDefinido = false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Edicao getEdicao() {
		return edicao;
	}

	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
	}

	public Time getCampeao() {
		return campeao;
	}

	public void setCampeao(Time campeao) {
		this.campeao = campeao;
	}

	public Time getViceCampeao() {
		return viceCampeao;
	}

	public void setViceCampeao(Time viceCampeao) {
		this.viceCampeao = viceCampeao;
	}

	public Time getTerceiroColocado() {
		return terceiroColocado;
	}

	public void setTerceiroColocado(Time terceiroColocado) {
		this.terceiroColocado = terceiroColocado;
	}

	public boolean isCampeaoDefinido() {
		return campeaoDefinido;
	}

	public void setCampeaoDefinido(boolean campeaoDefinido) {
		this.campeaoDefinido = campeaoDefinido;
	}

	public boolean isViceCampeaoDefinido() {
		return viceCampeaoDefinido;
	}

	public void setViceCampeaoDefinido(boolean viceCampeaoDefinido) {
		this.viceCampeaoDefinido = viceCampeaoDefinido;
	}

	public boolean isTerceiroColocadoDefinido() {
		return terceiroColocadoDefinido;
	}

	public void setTerceiroColocadoDefinido(boolean terceiroColocadoDefinido) {
		this.terceiroColocadoDefinido = terceiroColocadoDefinido;
	}	
	
	
}
