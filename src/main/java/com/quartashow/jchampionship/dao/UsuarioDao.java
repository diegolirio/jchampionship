package com.quartashow.jchampionship.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Usuario;

@Repository("usuarioDao")
public class UsuarioDao extends AbstractGenericDAO<Usuario> {

	public boolean login(Usuario usuario) {
		Query query = this.manager.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha");
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		return query.getResultList().size() > 0;
	}

}
