package br.com.projeto.crud.loja.dto;

import br.com.projeto.crud.loja.modelo.Usuario;

public class UsuarioDto {

	private Long id;
	private String nome;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}