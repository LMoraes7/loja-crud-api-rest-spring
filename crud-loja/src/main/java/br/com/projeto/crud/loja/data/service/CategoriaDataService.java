package br.com.projeto.crud.loja.data.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.loja.modelo.Categoria;
import br.com.projeto.crud.loja.repository.CategoriaRepository;

@Service
public class CategoriaDataService implements DataService{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public Page<Categoria> consultarTodos(Pageable pageable) {
		return this.categoriaRepository.findAll(pageable);
	}

	@Override
	public Optional<Categoria> consultarPorId(Long id) {
		return this.categoriaRepository.findById(id);
	}

	@Override
	public void deletarPorId(Long id) {
		this.categoriaRepository.deleteById(id);
	}
}