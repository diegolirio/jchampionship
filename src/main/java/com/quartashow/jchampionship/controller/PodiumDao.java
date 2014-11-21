package com.quartashow.jchampionship.controller;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.dao.AbstractGenericDAO;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Podium;

@Repository("podiumDao")
public class PodiumDao extends AbstractGenericDAO<Podium> {

	public Podium get(Edicao edicao) {
		Query query = super.manager.createQuery("Select p from Podium p where p.edicao.id = :edicaoId");
		query.setParameter("edicaoId", edicao.getId());
		try {
			return (Podium) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	
	
}
