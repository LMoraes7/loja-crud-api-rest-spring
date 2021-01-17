package br.com.projeto.crud.loja.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.crud.loja.data.service.CategoriaDataService;
import br.com.projeto.crud.loja.data.service.ProdutoDataService;
import br.com.projeto.crud.loja.dto.ProdutoDetalhadoDto;
import br.com.projeto.crud.loja.dto.ProdutoDto;
import br.com.projeto.crud.loja.form.ProdutoAtualizaForm;
import br.com.projeto.crud.loja.form.ProdutoForm;
import br.com.projeto.crud.loja.modelo.Produto;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoDataService produtoService;
	
	@Autowired
	private CategoriaDataService categoriaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDetalhadoDto> listarProduto(@PathVariable("id") Long id) {
		Optional<Produto> produtoOptional = this.produtoService.consultarTodosOsDados(id);
		if(produtoOptional.isPresent())
			return ResponseEntity.ok(new ProdutoDetalhadoDto(produtoOptional.get()));
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cadastrar")
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm produtoForm , UriComponentsBuilder uriBuilder) {
		Produto produto = produtoForm.converter(categoriaService);
		this.produtoService.salvar(produto);
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}
	
	@PutMapping("/atualizar/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable("id") Long id, @RequestBody @Valid ProdutoAtualizaForm produtoForm) {
		Optional<Produto> produtoOptional = this.produtoService.consultarPorId(id);
		if(produtoOptional.isPresent()) {
			Produto produto = produtoOptional.get();
			produto = produtoForm.atualizar(produto);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		Optional<Produto> produtoOptional = this.produtoService.consultarPorId(id);
		if(produtoOptional.isPresent()) {
			this.produtoService.deletarPorId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}