package com.quartashow.jchampionship.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.Jogo;

@Repository("jogadorDao")
public class JogadorDao extends AbstractGenericDAO<Jogador> {

	@Autowired
	private GrupoDao grupoDao;
	
	@Autowired
	private ClassificacaoDao classificacaoDao;

	@SuppressWarnings("unchecked")
	public List<Jogador> getJogadoresNaoEscalados(Jogo jogo) {
		String sql = "select j from Jogador j where j.id not In ( select je.jogador.id from JogadorEscalado je" +
					                                                 " where je.escalacao.jogo.id = :jogoId )";
		Query query = this.manager.createQuery(sql);
		query.setParameter("jogoId", jogo.getId());
		return (List<Jogador>) query.getResultList();
	}

	public List<Jogador> getJogadoresByEdicao(Edicao edicao) {
		List<Jogador> jogadores = new ArrayList<Jogador>();
		for(Grupo g : this.grupoDao.getGruposByEdicao(edicao)) {
			for(Classificacao c : this.classificacaoDao.getClassificacoesByGrupo(g)) {
				for(Jogador j : c.getTime().getJogadores()) {
					jogadores.add(j);
				}
			}
		}
		return jogadores;
	}

}
