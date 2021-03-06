package com.quartashow.jchampionship.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Escalacao {

	@Id @GeneratedValue
	private long id; 
	
	@OneToOne
	private Jogo jogo;
	
	@OneToMany(mappedBy="escalacao", fetch=FetchType.EAGER)
	private List<JogadorEscalado> jogadoresEscalados;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<JogadorEscalado> getJogadoresEscalados() {
		return jogadoresEscalados;
	}

	public void setJogadoresEscalados(List<JogadorEscalado> jogadoresEscalados) {
		this.jogadoresEscalados = jogadoresEscalados;
	}

	@Override
	public String toString() {
		return "Escalacao [id=" + id + ", jogo=" + jogo + "]";
	}
	
	
}
