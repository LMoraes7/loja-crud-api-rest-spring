package br.com.projeto.crud.loja.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.projeto.crud.loja.data.service.CategoriaDataService;
import br.com.projeto.crud.loja.modelo.Categoria;
import br.com.projeto.crud.loja.modelo.Produto;

public class ProdutoForm {

	@NotBlank(message = "O campo nome não pode ser vazio")
	private String nome;
	@NotBlank(message = "O campo descrição não pode ser vazio")
	private String descricao;
	@NotBlank(message = "O campo preço não pode ser vazio")
	private String preco;
	private List<CategoriaForm> categoriasForm = new ArrayList<>();

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

	public List<CategoriaForm> getCategoriasForm() {
		return categoriasForm;
	}

	public void setCategoriasForm(List<CategoriaForm> categoriasForm) {
		this.categoriasForm = categoriasForm;
	}

	public Produto converter(CategoriaDataService categoriaService) {
		List<Categoria> categorias = buscarCategorias(categoriaService);
		return new Produto(this.nome, this.descricao, new BigDecimal(this.preco), categorias);
	}

	private List<Categoria> buscarCategorias(CategoriaDataService categoriaService) {
		List<Categoria> lista = new ArrayList<Categoria>();
		
		this.categoriasForm.stream().forEach(categoria -> {
			Optional<Categoria> categoriaOptional = categoriaService.consultarPorId(categoria.getId());
			if(categoriaOptional.isPresent())
				lista.add(categoriaOptional.get());
		});
		return lista;
	}
}