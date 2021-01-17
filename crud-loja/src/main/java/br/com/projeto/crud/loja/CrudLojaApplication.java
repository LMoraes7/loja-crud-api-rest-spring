package br.com.projeto.crud.loja;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projeto.crud.loja.data.service.AvaliacaoDataService;
import br.com.projeto.crud.loja.data.service.CategoriaDataService;
import br.com.projeto.crud.loja.data.service.ProdutoDataService;
import br.com.projeto.crud.loja.data.service.UsuarioDataService;
import br.com.projeto.crud.loja.modelo.Avaliacao;
import br.com.projeto.crud.loja.modelo.Categoria;
import br.com.projeto.crud.loja.modelo.Produto;
import br.com.projeto.crud.loja.modelo.Usuario;

@SpringBootApplication
public class CrudLojaApplication implements CommandLineRunner{

	
	@Autowired
	private CategoriaDataService categoriaService;
	
	@Autowired
	private AvaliacaoDataService avaliacaoService;
	
	@Autowired
	private ProdutoDataService produtoService;
	
	@Autowired
	private UsuarioDataService usuarioService;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudLojaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario diego = this.usuarioService.consultarPorId(1L).get();
		Usuario carlos = this.usuarioService.consultarPorId(2L).get();
		
		List<Categoria> categorias = gerarCategorias();
		
		Produto produto = new Produto("Celular", "Celular Samsung J5", new BigDecimal("799.90"), categorias);
		Produto produto2 = new Produto("TV Smart", "SmartTv LG 42", new BigDecimal("1799.90"), categorias.get(0));
		Produto produto3 = new Produto("Celular Motorola", "Celular Moto G8", new BigDecimal("1299.90"), categorias.get(1));
		this.produtoService.salvar(produto);
		this.produtoService.salvar(produto2);
		this.produtoService.salvar(produto3);
		
		Avaliacao avaliacao = new Avaliacao("Celular muito bom, vale a pena comprar!", produto, diego);
		Avaliacao avaliacao2 = new Avaliacao("Celular com ótimo custo benefício!", produto, carlos);
		Avaliacao avaliacao3 = new Avaliacao("Celular com ótimo custo benefício!", produto3, carlos);
		Avaliacao avaliacao4 = new Avaliacao("Televisão muito boa, vale a pena comprar!", produto2, diego);
		this.avaliacaoService.salvar(avaliacao);
		this.avaliacaoService.salvar(avaliacao2);
		this.avaliacaoService.salvar(avaliacao3);
		this.avaliacaoService.salvar(avaliacao4);
		
		produto.addAvaliacao(avaliacao);
		produto.addAvaliacao(avaliacao2);
		produto2.addAvaliacao(avaliacao4);
		produto3.addAvaliacao(avaliacao3);
		this.produtoService.salvar(produto);
		this.produtoService.salvar(produto2);
		this.produtoService.salvar(produto3);
	}

	private List<Categoria> gerarCategorias() {
		Categoria categoriaEletro = this.categoriaService.consultarPorId(1L).get();
		Categoria categoriaSmartphone = this.categoriaService.consultarPorId(2L).get();
		Categoria categoriaSamsung = this.categoriaService.consultarPorId(3L).get();
		return Arrays.asList(categoriaEletro, categoriaSmartphone, categoriaSamsung);
	}
}