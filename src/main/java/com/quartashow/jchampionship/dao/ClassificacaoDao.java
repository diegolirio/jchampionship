package com.quartashow.jchampionship.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.Grupo;

@Repository("classificacaoDao")
public class ClassificacaoDao extends AbstractGenericDAO<Classificacao> {

	public List<Classificacao> getClassificacoesByGrupo(Grupo grupo) {
		Query query = this.manager.createQuery("Select c from Classificacao c where c.grupo.id = :idGrupo");
		query.setParameter("idGrupo", grupo.getId());
		@SuppressWarnings("unchecked")
		List<Classificacao> list = query.getResultList();
		return list;
	}

}
