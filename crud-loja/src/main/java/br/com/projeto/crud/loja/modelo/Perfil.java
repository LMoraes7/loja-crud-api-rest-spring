package br.com.projeto.crud.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority, Entidade{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String perfil;

	public Long getId() {return id;}

	public String getPerfil() {return perfil;}

	@Override
	public String getAuthority() {return this.perfil;}
}