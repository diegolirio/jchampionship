package com.quartashow.jchampionship.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Jogo;

@Repository("jogoDao")
public class JogoDao extends AbstractGenericDAO<Jogo> {

	@SuppressWarnings("unchecked")
	public List<Jogo> getJogosByEdicao(Edicao edicao) {
		Query createQuery = super.manager.createQuery("Select j from Jogo j where j.grupo.edicao.id = :edicaoId");
		createQuery.setParameter("edicaoId", edicao.getId());
		return (List<Jogo>) createQuery.getResultList();  
	}

	@SuppressWarnings("unchecked")
	public List<Jogo> getJogosByGrupo(Grupo grupo) {
		Query createQuery = super.manager.createQuery("Select j from Jogo j where j.grupo.id = :grupoId");
		createQuery.setParameter("grupoId", grupo.getId());
		return (List<Jogo>) createQuery.getResultList();  
	}

	@SuppressWarnings("unchecked")
	public List<Jogo> getJogosByGrupoAndRodada(Grupo grupo, long rodada) {
		Query createQuery = super.manager.createQuery("Select j from Jogo j where j.grupo.id = :grupoId and j.rodada = :rodada");
		createQuery.setParameter("grupoId", grupo.getId());
		createQuery.setParameter("rodada", rodada);
		return (List<Jogo>) createQuery.getResultList();  
	}
	
}
