package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Evento {

	@Id @GeneratedValue
	private long id;
	
	private String descricao;
	
	//@ManyToMany(mappedBy="eventos")
	//private List<JogadorEscalado> jogadoresEscalados;
	
	@OneToMany(mappedBy="evento")
	private List<CollectionEventos> collectionEventos;

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

	public List<CollectionEventos> getCollectionEventos() {
		return collectionEventos;
	}

	public void setCollectionEventos(List<CollectionEventos> collectionEventos) {
		this.collectionEventos = collectionEventos;
	}	
	
	
}
