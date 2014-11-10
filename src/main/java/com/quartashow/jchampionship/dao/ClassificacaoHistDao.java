package com.quartashow.jchampionship.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.mockito.Mock;
import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.ClassificacaoHist;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Time;

@Repository("classificacaoHistDao")
public class ClassificacaoHistDao extends AbstractGenericDAO<ClassificacaoHist> {

	@Mock
	protected EntityManager manager;
	
	public boolean existHist(Time time, Grupo grupo, long rodada) {
		Query query = this.manager.createQuery("Select h from ClassificacaoHist h where h.time.id = :timeId and h.grupo.id = :grupoId and h.rodada = :rodada");
		query.setParameter("timeId", time.getId());
		query.setParameter("grupoId", grupo.getId());
		query.setParameter("rodada", rodada);
		return query.getResultList().size() > 0;
	}
	
	public int getNumberHistLastRodada() {
		Query query = this.manager.createQuery("Select max(h.rodada) from ClassificacaoHist h");
		try {
			return (int) query.getSingleResult();
		} catch(Exception e) {
			return -1;
		}
	}	

	@SuppressWarnings("unchecked")
	public List<ClassificacaoHist> getHistListByRodada(long rodada) {
		Query query = this.manager.createQuery("Select h from ClassificacaoHist h where h.rodada = :rodada");
		query.setParameter("rodada", rodada);
		return (List<ClassificacaoHist>) query.getResultList();
		
	}

}
