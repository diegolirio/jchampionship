package com.quartashow.jchampionship.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.Jogo;

@Repository("jogadorDao")
public class JogadorDao extends AbstractGenericDAO<Jogador> {

	@SuppressWarnings("unchecked")
	public List<Jogador> getJogadoresNaoEscalados(Jogo jogo) {
		String sql = "select j from Jogador j where j.id not In ( select je.jogador.id from JogadorEscalado je" +
					                                                 " where je.escalacao.jogo.id = :jogoId )";
		Query query = this.manager.createQuery(sql);
		query.setParameter("jogoId", jogo.getId());
		return (List<Jogador>) query.getResultList();
	}

}
