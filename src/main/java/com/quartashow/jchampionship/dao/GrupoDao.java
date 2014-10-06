package com.quartashow.jchampionship.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Grupo;

@Repository("grupoDao")
public class GrupoDao extends AbstractGenericDAO<Grupo> {

	@SuppressWarnings("unchecked")
	public List<Grupo> getGruposByEdicao(Edicao edicao) {
		Query createQuery = super.manager.createQuery("Select g from Grupo g where g.edicao.id = :edicaoId");
		createQuery.setParameter("edicaoId", edicao.getId());
		return (List<Grupo>) createQuery.getResultList();  
	}

}
