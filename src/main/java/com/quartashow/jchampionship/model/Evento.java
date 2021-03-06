package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Evento {

	@Id @GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String descricao;
	
	private String imgName;
	
	//@ManyToMany(mappedBy="eventos")
	//private List<JogadorEscalado> jogadoresEscalados;
	
	@OneToMany(mappedBy="evento")
	private List<CollectionEventos> collectionEventos;

	public Evento() {}
	
	public Evento(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
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
