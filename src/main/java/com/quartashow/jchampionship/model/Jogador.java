package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class Jogador {

	@Id @GeneratedValue
	private long id;
	
	@NotNull @Size(min=2, max=40)
	private String nome;
	
	@JsonBackReference
	@ManyToMany(mappedBy="jogadores")
	private List<Time> times;
	
	@NotNull
	@ManyToOne
	private Posicao posicao;
	
	private String uriFoto = "/jchampionship/static/quartashow/img/jogadores/jogador_no_photo.png";

	public Jogador() {}
	
	public Jogador(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

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

	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}
	
	public String getUriFoto() {
		return uriFoto;
	}

	public void setUriFoto(String uriFoto) {
		this.uriFoto = uriFoto;
	}

	@Override
	public String toString() {
		return "Jogador [id=" + id + ", nome=" + nome
				+ ", posicao=" + posicao + "]";
	}
	
	
	
}
