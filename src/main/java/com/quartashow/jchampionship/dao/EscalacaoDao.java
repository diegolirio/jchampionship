package com.quartashow.jchampionship.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.Jogo;

@Repository("EscalacaoDao")
public class EscalacaoDao extends AbstractGenericDAO<Escalacao> {

	public Escalacao get(Jogo jogo) {
		Query query = this.manager.createQuery("Select e from Escalacao e where e.jogo.id = :jogoId");
		query.setParameter("jogoId", jogo.getId());
		try {
			return (Escalacao) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

}
