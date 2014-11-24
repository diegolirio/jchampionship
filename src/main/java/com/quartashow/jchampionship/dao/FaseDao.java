package com.quartashow.jchampionship.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Fase;

@Repository("faseDao")
public class FaseDao extends AbstractGenericDAO<Fase> {

	public Fase get(char sigla) {
		Query query = super.manager.createQuery("Select f from Fase f where f.sigla = :sigla");
		query.setParameter("sigla", sigla);
		try {
			return (Fase) query.getSingleResult();
		} catch(NoResultException e) {
			return null; 
		}
	}

}
