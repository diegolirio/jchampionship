package com.quartashow.jchampionship.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Status {

	@Id @GeneratedValue
	private long id;
	
	@Column(unique=true)
	String descricao; // Pendencia do Formulario | Andamento | Finalizado | 

	private String imgName;

	public Status() {}

	public Status(long id) {
		this.id = id;
	}
	
	public Status(String descricao) {
		this.descricao = descricao;
	}

	public Status(long id, String descricao) {
		this(id);
		this.descricao = descricao;
	}

	public Status(String descricao, String imgName) {
		this(descricao);
		this.imgName = imgName;
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

	public void setDescricao(String nome) {
		this.descricao = nome;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	
	
}
