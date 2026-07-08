package br.edu.ifg.academico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifg.academico.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

}
