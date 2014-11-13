package com.quartashow.jchampionship.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.Grupo;

@Repository("classificacaoDao")
public class ClassificacaoDao extends AbstractGenericDAO<Classificacao> {

	public List<Classificacao> getClassificacoesByGrupo(Grupo grupo) {
		Query query = this.manager.createQuery("Select c from Classificacao c where c.grupo.id = :idGrupo order by c.colocacao");
		query.setParameter("idGrupo", grupo.getId());
		@SuppressWarnings("unchecked")
		List<Classificacao> list = query.getResultList();
		return list;
	}

	public List<Classificacao> getLideres(Grupo grupo) {
		Query query = manager.
						createQuery("Select c from Classificacao c where c.grupo.id = :grupoId " +
									" and c.pontos = (Select max(cp.pontos) from Classificacao cp where cp.grupo.id = c.grupo.id )" +
								    " and (c.golsPro - c.golsContra) = (Select max(cs.golsPro - cs.golsContra) from Classificacao cs where cs.grupo.id = c.grupo.id )");
		query.setParameter("grupoId", grupo.getId());
		@SuppressWarnings("unchecked")
		List<Classificacao> list = (List<Classificacao>) query.getResultList();
		return list;
	}

}
