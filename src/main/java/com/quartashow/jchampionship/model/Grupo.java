package com.quartashow.jchampionship.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Grupo {

	@Id @GeneratedValue
	private long id;
	
	@NotNull @Size(min=1, max=15)
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Edicao edicao;

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

	public Edicao getEdicao() {
		return edicao;
	}

	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", descricao=" + descricao + ", edicao="	+ edicao + "]";
	}
	
	
	
	
}
