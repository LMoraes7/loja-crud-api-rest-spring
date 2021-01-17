package br.com.projeto.crud.loja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.crud.loja.modelo.Avaliacao;
import br.com.projeto.crud.loja.modelo.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto>{

//	@Query("SELECT p FROM Produto p LEFT JOIN FETCH p.categorias LEFT JOIN FETCH p.avaliacoes WHERE p.id = :id")
//	Optional<Produto> findFiltro(@Param("id") Long id);
	
	@Query("SELECT DISTINCT p FROM Produto p LEFT JOIN FETCH p.categorias WHERE p.id = :id")
	Optional<Produto> findProdutoWithCategoria(@Param("id") Long id);
	
	@Query("SELECT a FROM Avaliacao a LEFT JOIN FETCH a.produto p WHERE p.id = :id")
	List<Avaliacao> findAvaliacaoOfProduto(@Param("id") Long id);
	
	@Query("SELECT p FROM Produto p LEFT JOIN FETCH p.avaliacoes WHERE p.id = :id")
	Optional<Produto> findProdutoWithAvaliacao(@Param("id") Long id);
}
