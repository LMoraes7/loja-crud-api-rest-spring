package br.com.projeto.crud.loja.data.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.loja.modelo.Avaliacao;
import br.com.projeto.crud.loja.repository.AvaliacaoRepository;

@Service
public class AvaliacaoDataService implements DataService{

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Override
	public Optional<Avaliacao> consultarPorId(Long id) {
		return this.avaliacaoRepository.findById(id);
	}

	@Override
	public Page<Avaliacao> consultarTodos(Pageable pageable) {
		return this.avaliacaoRepository.findAll(pageable);
	}

	@Override
	public void deletarPorId(Long id) {
		this.avaliacaoRepository.deleteById(id);
	}
	
	public void salvar(Avaliacao avaliacao) {
		this.avaliacaoRepository.save(avaliacao);
	}

}
