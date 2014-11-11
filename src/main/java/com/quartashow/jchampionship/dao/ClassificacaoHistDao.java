package com.quartashow.jchampionship.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.ClassificacaoHist;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Time;

@Repository("classificacaoHistDao")
public class ClassificacaoHistDao extends AbstractGenericDAO<ClassificacaoHist> {
	
	public boolean existHist(Time time, Grupo grupo, int rodada) {
		Query query = this.manager.createQuery("Select h from ClassificacaoHist h where h.time.id = :timeId and h.grupo.id = :grupoId and h.rodada = :rodada");
		query.setParameter("timeId", time.getId());
		query.setParameter("grupoId", grupo.getId());
		query.setParameter("rodada", rodada);
		return query.getResultList().size() > 0;
	}
	
	public int getNumberHistLastRodada(Grupo grupo) {
		Query query = this.manager.createQuery("Select max(h.rodada) from ClassificacaoHist h where h.grupo.id = :grupoId");
		query.setParameter("grupoId", grupo.getId());
		try {
			return (int) query.getSingleResult();
		} catch(Exception e) {
			return -1;
		}
	}	

	@SuppressWarnings("unchecked")
	public List<ClassificacaoHist> getHistListByRodada(int rodada, Grupo grupo) {
		Query query = this.manager.createQuery("Select h from ClassificacaoHist h where h.rodada = :rodada and h.grupo.id = :grupoId");
		query.setParameter("rodada", rodada);
		query.setParameter("grupoId", grupo.getId());
		return (List<ClassificacaoHist>) query.getResultList();
	}

	public List<Classificacao> getClassificacoesHistByGrupo(Grupo grupo) {
		Query query = this.manager.createQuery("Select c from ClassificacaoHist c where c.grupo.id = :idGrupo order by c.rodada, c.colocacao");
		query.setParameter("idGrupo", grupo.getId());
		@SuppressWarnings("unchecked")
		List<Classificacao> list = query.getResultList();
		return list;
	}

}
