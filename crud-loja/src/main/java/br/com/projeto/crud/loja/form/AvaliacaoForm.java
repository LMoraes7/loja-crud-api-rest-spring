package br.com.projeto.crud.loja.form;

import java.time.LocalDate;

import br.com.projeto.crud.loja.modelo.Avaliacao;
import br.com.projeto.crud.loja.modelo.Produto;
import br.com.projeto.crud.loja.modelo.Usuario;

public class AvaliacaoForm {

	private String avaliacao;

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Avaliacao converter(Produto produto, Usuario usuario) {
		return new Avaliacao(this.avaliacao, produto, usuario);
	}

	public Avaliacao atualizar(Avaliacao avaliacao) {
		avaliacao.setAvaliacao(this.avaliacao);
		avaliacao.setData(LocalDate.now());
		return avaliacao;
	}
}