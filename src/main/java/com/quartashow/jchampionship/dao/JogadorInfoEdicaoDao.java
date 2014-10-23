package com.quartashow.jchampionship.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.JogadorInfoEdicao;

@Repository("jogadorInfoEdicaoDao")
public class JogadorInfoEdicaoDao extends AbstractGenericDAO<JogadorInfoEdicao> {

	public boolean exists(Jogador jogador, Edicao edicao) {
		Query query = this.manager.createQuery("select jie from JogadorEscalado jie where jie.jogador.id = :jogadorId and jie.edicao.id = :edicaoId");
		query.setParameter("jogadorId", jogador.getId());
		query.setParameter("edicaoId", edicao.getId());
		try {
			JogadorInfoEdicao jogadorInfoEdicao = (JogadorInfoEdicao) query.getSingleResult();
			return jogadorInfoEdicao.getId() > 0;
		} catch (NoResultException e) {
			return false;
		}
	}	
	
}
