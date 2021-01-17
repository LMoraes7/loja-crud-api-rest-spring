package br.com.projeto.crud.loja.dto;

import java.time.LocalDate;

import br.com.projeto.crud.loja.modelo.Avaliacao;

public class AvaliacaoDto {

	private Long id;
	private String avaliacao;
	private LocalDate data;
	private UsuarioDto autor;
	private ProdutoDto produto;

	public AvaliacaoDto(Avaliacao avaliacao) {
		this.id = avaliacao.getId();
		this.avaliacao = avaliacao.getAvaliacao();
		this.data = avaliacao.getData();
		this.autor = new UsuarioDto(avaliacao.getAutor());
		this.produto = new ProdutoDto(avaliacao.getProduto());
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

	public ProdutoDto getProduto() {
		return produto;
	}
}