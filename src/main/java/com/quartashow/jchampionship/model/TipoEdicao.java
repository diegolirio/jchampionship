package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class TipoEdicao {

	@Id @GeneratedValue
	public long id;
	
	@NotNull @Size(min=2)
	public String descricao;

	@JsonBackReference
	@OneToMany(mappedBy="tipoEdicao")
	private List<Edicao> edicoes;
	
	public TipoEdicao() {}
	
	public TipoEdicao(long id) {
		this.id = id;
	}

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

	public List<Edicao> getEdicoes() {
		return edicoes;
	}

	public void setEdicoes(List<Edicao> edicoes) {
		this.edicoes = edicoes;
	}
	
}
