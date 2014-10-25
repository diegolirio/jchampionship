package com.quartashow.jchampionship.dao;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Campeonato;

@Repository("campeonatoDao")
public class CampeonatoDao extends AbstractGenericDAO<Campeonato> {

//	public List<Campeonato> getCampeonatosByUsuario(Usuario usuario) {
//		List<Campeonato> list = this.getList(Campeonato.class);
//		List<Campeonato> listReturn = new ArrayList<Campeonato>(); 
//		for (Campeonato campeonato : list) {
//			for (Usuario u : campeonato.getUsuarios()) {
//				if(u.getId() == usuario.getId())
//					listReturn.add(campeonato);	
//			}
//		}
//		return listReturn;
//	}

}
