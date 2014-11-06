package com.quartashow.jchampionship.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.JogadorInfoEdicao;

@Repository("jogadorInfoEdicaoDao")
public class JogadorInfoEdicaoDao extends AbstractGenericDAO<JogadorInfoEdicao> {

	public boolean exists(Jogador jogador, Edicao edicao) {
		Query query = this.manager.createQuery("select jie from JogadorInfoEdicao jie where jie.jogador.id = :jogadorId and jie.edicao.id = :edicaoId");
		query.setParameter("jogadorId", jogador.getId());
		query.setParameter("edicaoId", edicao.getId());
		try {
			JogadorInfoEdicao jogadorInfoEdicao = (JogadorInfoEdicao) query.getSingleResult();
			return jogadorInfoEdicao.getId() > 0;
		} catch (NoResultException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<JogadorInfoEdicao> getJogadoresInfoEdicaoByEdicao(Edicao edicao) {
		Query query = this.manager.createQuery("Select jie from JogadorInfoEdicao jie where jie.edicao.id = :edicaoId order by jie.gols desc");
		query.setParameter("edicaoId", edicao.getId());
		return (List<JogadorInfoEdicao>) query.getResultList();
	}

	public JogadorInfoEdicao get(Edicao edicao, Jogador jogador) {
		Query query = this.manager.createQuery("select jie from JogadorInfoEdicao jie where jie.jogador.id = :jogadorId and jie.edicao.id = :edicaoId");
		query.setParameter("jogadorId", jogador.getId());
		query.setParameter("edicaoId", edicao.getId());
		try {
			return (JogadorInfoEdicao) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}				
	}	
	
}
