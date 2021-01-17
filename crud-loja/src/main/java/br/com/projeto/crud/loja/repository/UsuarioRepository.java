package br.com.projeto.crud.loja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.crud.loja.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.perfis WHERE u.username = :username")
	Optional<Usuario> findUsername(@Param("username") String username);
}
