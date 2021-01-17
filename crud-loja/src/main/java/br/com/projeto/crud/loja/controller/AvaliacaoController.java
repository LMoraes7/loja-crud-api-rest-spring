package br.com.projeto.crud.loja.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

import br.com.projeto.crud.loja.data.service.AvaliacaoDataService;
import br.com.projeto.crud.loja.data.service.ProdutoDataService;
import br.com.projeto.crud.loja.dto.AvaliacaoDto;
import br.com.projeto.crud.loja.form.AvaliacaoForm;
import br.com.projeto.crud.loja.modelo.Avaliacao;
import br.com.projeto.crud.loja.modelo.Produto;
import br.com.projeto.crud.loja.modelo.Usuario;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoDataService avalicaoService;
	
	@Autowired
	private ProdutoDataService produtoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<AvaliacaoDto> listar(@PathVariable("id") Long id) {
		Optional<Avaliacao> avaliacaoOptional = this.avalicaoService.consultarPorId(id);
		if(avaliacaoOptional.isPresent())
			return ResponseEntity.ok(new AvaliacaoDto(avaliacaoOptional.get()));
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("{idNoticia}/cadastrar")
	@Transactional
	public ResponseEntity<AvaliacaoDto> cadastrar(@PathVariable("idNoticia") Long id, @RequestBody @Valid AvaliacaoForm avalicaoForm, 
			UriComponentsBuilder uriBuilder) {
		Optional<Produto> produtoOptional = this.produtoService.consultarComAvaliacoes(id);
		if(produtoOptional.isPresent()) {
			Produto produto = produtoOptional.get();
			Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Avaliacao avaliacao = avalicaoForm.converter(produto, usuario);
			this.avalicaoService.salvar(avaliacao);
			produto.addAvaliacao(avaliacao);
			URI uri = uriBuilder.path("/avaliacoes/{id}").buildAndExpand(avaliacao.getId()).toUri();
			return ResponseEntity.created(uri).body(new AvaliacaoDto(avaliacao));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/atualizar/{id}")
	@Transactional
	public ResponseEntity<AvaliacaoDto> atualizar(@PathVariable("id") Long id, @RequestBody @Valid AvaliacaoForm avaliacaoForm) {
		Optional<Avaliacao> avaliacaoOptional = this.avalicaoService.consultarPorId(id);
		if(avaliacaoOptional.isPresent()) {
			Avaliacao avaliacao = avaliacaoOptional.get();
			avaliacao = avaliacaoForm.atualizar(avaliacao);
			return ResponseEntity.ok(new AvaliacaoDto(avaliacao));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		Optional<Avaliacao> avaliacaoOptional = this.avalicaoService.consultarPorId(id);
		if(avaliacaoOptional.isPresent()) {
			this.avalicaoService.deletarPorId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}