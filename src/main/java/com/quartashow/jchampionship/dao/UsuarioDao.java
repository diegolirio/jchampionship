package com.quartashow.jchampionship.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.quartashow.jchampionship.model.Usuario;

@Repository("usuarioDao")
public class UsuarioDao extends AbstractGenericDAO<Usuario> {

	public boolean login(Usuario usuario) {
		Query query = this.manager.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha");
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return usuario.getId() > 0;
	}

	

	public Usuario get(String email) {
		Query query = this.manager.createQuery("Select u from Usuario u where u.email = :email");
		query.setParameter("email", email);
		Usuario usuario = (Usuario) query.getSingleResult();
		return usuario;
	}

}
