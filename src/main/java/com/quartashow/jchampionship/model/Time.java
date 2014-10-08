package com.quartashow.jchampionship.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Time {

	@Id @GeneratedValue
	private long id;

	@NotNull @Size(min=3, max=25)
	private String nome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Time [id=" + id + ", nome=" + nome + "]";
	}
	
	
}
