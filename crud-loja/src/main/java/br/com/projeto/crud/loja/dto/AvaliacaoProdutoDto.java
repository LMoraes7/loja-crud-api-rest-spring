package br.com.projeto.crud.loja.dto;

import java.time.LocalDate;

import br.com.projeto.crud.loja.modelo.Avaliacao;

public class AvaliacaoProdutoDto {

	private Long id;
	private String avaliacao;
	private LocalDate data;
	private UsuarioDto autor;

	public AvaliacaoProdutoDto(Avaliacao avaliacao) {
		this.id = avaliacao.getId();
		this.avaliacao = avaliacao.getAvaliacao();
		this.data = avaliacao.getData();
		this.autor = new UsuarioDto(avaliacao.getAutor());
	}

	public Long getId() {
		return id;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public LocalDate getData() {
		return data;
	}

	public UsuarioDto getAutor() {
		return autor;
	}
}