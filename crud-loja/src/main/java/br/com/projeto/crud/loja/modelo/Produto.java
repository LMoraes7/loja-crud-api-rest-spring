package br.com.projeto.crud.loja.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Produto implements Entidade{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Categoria> categorias = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL)
	private List<Avaliacao> avaliacoes = new ArrayList<>();

	public Produto() {
	}

	public Produto(String nome, String descricao, BigDecimal preco, List<Categoria> categorias) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categorias = categorias;
	}

	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categorias.add(categoria);
	}

	public Long getId() {
		return id;
	}

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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public List<Avaliacao> getAvaliacoes() {
		return Collections.unmodifiableList(avaliacoes);
	}

	public void addAvaliacao(Avaliacao avaliacao) {
		this.avaliacoes.add(avaliacao);
	}
	
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}