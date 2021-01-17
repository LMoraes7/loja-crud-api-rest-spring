package br.com.projeto.crud.loja.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Avaliacao implements Entidade{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String avaliacao;
	private LocalDate data = LocalDate.now();

	@ManyToOne
	private Usuario autor;

	@ManyToOne
	private Produto produto;
	
	public Avaliacao() {
	}

	public Avaliacao(String avaliacao, Produto produto, Usuario autor) {
		this.avaliacao = avaliacao;
		this.produto = produto;
		this.autor = autor;
	}

	public Long getId() {
		return id;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Usuario getAutor() {
		return autor;
	}

	public Produto getProduto() {
		return produto;
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
		Avaliacao other = (Avaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}