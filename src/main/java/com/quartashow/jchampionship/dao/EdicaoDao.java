package com.quartashow.jchampionship.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Status;

@Repository("edicaoDao")
public class EdicaoDao extends AbstractGenericDAO<Edicao> {

	@SuppressWarnings("unchecked")
	public List<Edicao> getListByStatus(Status status) {
		Query createQuery = super.manager.createQuery("Select e from Edicao e where e.status.id = :idStatus");
		createQuery.setParameter("idStatus", status.getId());
		return (List<Edicao>) createQuery.getResultList();
	}

}
