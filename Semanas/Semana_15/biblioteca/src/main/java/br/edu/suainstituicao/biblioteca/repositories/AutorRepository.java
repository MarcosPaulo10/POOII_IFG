package br.edu.suainstituicao.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.suainstituicao.biblioteca.entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
