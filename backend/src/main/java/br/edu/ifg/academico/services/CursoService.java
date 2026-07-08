package br.edu.ifg.academico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifg.academico.entities.Curso;
import br.edu.ifg.academico.repositories.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	CursoRepository cursoRep;
	
	public Curso insert(Curso c) {
		return cursoRep.save(c);
	}
	
	public void delete(Integer id) {
		cursoRep.deleteById(id);
	}
	
	public List<Curso> findAll() {
		return cursoRep.findAll();
	}
	
	public Curso findById(Integer id) {
		return cursoRep.findById(id)
					   .orElseThrow( 
							 () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
						);
	}

	public Curso update(Integer id, Curso cursoAlt) {
		return cursoRep.findById(id).map(
										cursoDB -> {
											cursoDB.setNomeCurso(cursoAlt.getNomeCurso());
											return cursoRep.save(cursoDB);
										}
						)
						.orElseThrow(
								() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
						);
	}
}
