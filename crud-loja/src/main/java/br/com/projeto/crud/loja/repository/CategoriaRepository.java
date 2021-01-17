package br.com.projeto.crud.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.crud.loja.modelo.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
