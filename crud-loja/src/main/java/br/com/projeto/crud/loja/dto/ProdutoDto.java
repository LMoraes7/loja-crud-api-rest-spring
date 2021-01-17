package br.com.projeto.crud.loja.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import br.com.projeto.crud.loja.modelo.Produto;

public class ProdutoDto {
	
	public static Page<ProdutoDto> converter(Page<Produto> produtos) {
		return produtos.map(ProdutoDto::new);
	}

	private Long id;
	private String nome;
	private BigDecimal preco;

	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}
}