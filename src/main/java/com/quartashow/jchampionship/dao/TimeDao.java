package com.quartashow.jchampionship.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Time;

@Repository("timeDao")
public class TimeDao extends AbstractGenericDAO<Time> {

	@SuppressWarnings("unchecked")
	public List<Time> getTimesByEdicaoClassificacao(Edicao edicao) {
		String sql = "select t from Classificacao c, Grupo g, Time t where c.grupo.id = g.id and t.id = c.time.id and g.edicao.id = :edicaoId";
		Query query = this.manager.createQuery(sql);
		query.setParameter("edicaoId", edicao.getId());
		return (List<Time>) query.getResultList();
	}

}
