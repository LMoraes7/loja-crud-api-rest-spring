package br.com.projeto.crud.loja.form;

import java.math.BigDecimal;

import br.com.projeto.crud.loja.modelo.Produto;

public class ProdutoAtualizaForm {

	private String nome;
	private String descricao;
	private String preco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public Produto atualizar(Produto produto) {
		produto.setNome(this.nome);
		produto.setDescricao(this.descricao);
		produto.setPreco(new BigDecimal(this.preco));
		return produto;
	}
}