package com.quartashow.jchampionship.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.validator.constraints.Email;

@Entity
public class Usuario {

	@Id @GeneratedValue
	private long id;
	
	private String nome;
	
	@NotNull @Email @Size(min=1, message="campo obrigatorio")
	@Column(unique=true)
	private String email;
	
	@NotNull @Size(min=6, max=10)
	private String senha;
	
	
    @Temporal(TemporalType.DATE)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date dataCadastro;
	
    @JsonBackReference
	@ManyToMany(mappedBy="usuarios", fetch=FetchType.EAGER)
	private List<Campeonato> campeonatos;
    
    private boolean superUsuario = false;

    private String uriImage = "/jchampionship/static/quartashow/img/usuario/profile-user_fake.png";
    
	public Usuario() {}
	
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}
	
	public boolean isSuperUsuario() {
		return superUsuario;
	}

	public void setSuperUsuario(boolean superUsuario) {
		this.superUsuario = superUsuario;
	}
	public String getUriImage() {
		return uriImage;
	}

	public void setUriImage(String uriImage) {
		this.uriImage = uriImage;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email
				+ "]";
	}
	
	
	
	
}
