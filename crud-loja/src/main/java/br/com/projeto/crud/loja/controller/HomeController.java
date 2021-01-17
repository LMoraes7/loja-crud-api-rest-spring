package br.com.projeto.crud.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.crud.loja.data.service.ProdutoDataService;
import br.com.projeto.crud.loja.dto.ProdutoDto;
import br.com.projeto.crud.loja.modelo.Produto;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private ProdutoDataService produtoService;
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDto>> listar(@PageableDefault(page = 0, size = 5, direction = Direction.ASC, sort = "preco") Pageable pageable) {
		Page<Produto> produtos = this.produtoService.consultarTodos(pageable);
		return ResponseEntity.ok(ProdutoDto.converter(produtos));
	}
	
	@GetMapping("/{page}")
	public ResponseEntity<Page<ProdutoDto>> listarPage(@PathVariable("page") Integer page) {
		Pageable pageable = PageRequest.of(page, 5, Direction.ASC, "preco");
		return ResponseEntity.ok(ProdutoDto.converter(this.produtoService.consultarTodos(pageable)));
	}
	
	@GetMapping("/busca")
	public ResponseEntity<Page<ProdutoDto>> listarBusca(@RequestParam("query") String query, 
			@PageableDefault(page = 0, size = 5, direction = Direction.ASC, sort = "preco") Pageable pageable) {
		return ResponseEntity.ok(ProdutoDto.converter(this.produtoService.consultarPorFiltros(query, pageable)));
	}
	
	@GetMapping("/busca/{page}")
	public ResponseEntity<Page<ProdutoDto>> listarBuscaPage(@PathVariable("page") Integer page,
			@RequestParam("query") String query) {
		Pageable pageable = PageRequest.of(page, 5, Direction.ASC, "preco");
		return ResponseEntity.ok(ProdutoDto.converter(this.produtoService.consultarPorFiltros(query, pageable)));
	}
}