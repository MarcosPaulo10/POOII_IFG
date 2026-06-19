package br.edu.suainstituicao.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.suainstituicao.biblioteca.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	List<Livro> findByAutorId(Long autorId);

	boolean existsByIsbn(String isbn);

}
