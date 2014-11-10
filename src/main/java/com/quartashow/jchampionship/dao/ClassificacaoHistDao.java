package com.quartashow.jchampionship.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.ClassificacaoHist;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Time;

@Repository("classificacaoHistDao")
public class ClassificacaoHistDao extends AbstractGenericDAO<ClassificacaoHist> {

	public boolean existHist(Time time, Grupo grupo, String rodada) {
		Query query = this.manager.createQuery("Select h from ClassificacaoHist h where h.time.id = :timeId and h.grupo.id = :grupoId and h.rodada = :rodada");
		query.setParameter("timeId", time.getId());
		query.setParameter("grupoId", grupo.getId());
		query.setParameter("rodada", rodada);
		return query.getResultList().size() > 0;
	}

}
