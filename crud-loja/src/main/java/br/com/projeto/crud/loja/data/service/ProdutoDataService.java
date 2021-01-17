package br.com.projeto.crud.loja.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.loja.modelo.Avaliacao;
import br.com.projeto.crud.loja.modelo.Produto;
import br.com.projeto.crud.loja.repository.ProdutoRepository;
import br.com.projeto.crud.loja.specification.ProdutoSpecification;

@Service
public class ProdutoDataService implements DataService{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public Optional<Produto> consultarPorId(Long id) {
		return this.produtoRepository.findById(id);
	}

	@Override
	public Page<Produto> consultarTodos(Pageable pageable) {
		return this.produtoRepository.findAll(pageable);
	}

	@Override
	public void deletarPorId(Long id) {
		this.produtoRepository.deleteById(id);
	}
	
	public Page<Produto> consultarPorFiltros(String query, Pageable pageable) {
		return this.produtoRepository.findAll(ProdutoSpecification.nome(query)
													.or(ProdutoSpecification.descricao(query)), pageable);
	}
	
	public void salvar(Produto produto) {
		this.produtoRepository.save(produto);
	}
	
	public Optional<Produto> consultarTodosOsDados(Long id) {
		Optional<Produto> produtoOptional= this.produtoRepository.findProdutoWithCategoria(id);
		if(produtoOptional.isPresent()) {
			Produto produto = produtoOptional.get();
			List<Avaliacao> avaliacoes = this.produtoRepository.findAvaliacaoOfProduto(id);
			produto.setAvaliacoes(avaliacoes);
			return produtoOptional;
		}
		return produtoOptional;
	}
	
	public Optional<Produto> consultarComAvaliacoes(Long id) {
		return this.produtoRepository.findProdutoWithAvaliacao(id);
	}
}