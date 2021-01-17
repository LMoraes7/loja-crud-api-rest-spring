package br.com.projeto.crud.loja.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.projeto.crud.loja.modelo.Produto;

public class ProdutoSpecification {

	public static Specification<Produto> nome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.like(root.get("nome"), "%"+nome+"%");
	}
	
	public static Specification<Produto> descricao(String descricao) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.like(root.get("descricao"), "%"+descricao+"%");
	}
}