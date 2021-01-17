package br.com.projeto.crud.loja.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.crud.loja.modelo.Produto;

public class ProdutoDetalhadoDto {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private List<CategoriaDto> categorias = new ArrayList<>();
	private List<AvaliacaoProdutoDto> avaliacoes = new ArrayList<>();

	public ProdutoDetalhadoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.categorias = produto.getCategorias().stream().map(CategoriaDto::new).collect(Collectors.toList());
		this.avaliacoes = produto.getAvaliacoes().stream().map(AvaliacaoProdutoDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public List<CategoriaDto> getCategorias() {
		return categorias;
	}

	public List<AvaliacaoProdutoDto> getAvaliacoes() {
		return avaliacoes;
	}
}