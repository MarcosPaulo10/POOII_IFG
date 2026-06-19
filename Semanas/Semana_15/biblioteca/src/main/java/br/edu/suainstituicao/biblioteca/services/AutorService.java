package br.edu.suainstituicao.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.suainstituicao.biblioteca.entities.Autor;
import br.edu.suainstituicao.biblioteca.repositories.AutorRepository;
import br.edu.suainstituicao.biblioteca.services.exceptions.ResourceNotFoundException;

@Service
public class AutorService {

	@Autowired
	AutorRepository autorRep;

	public Autor insert(Autor a) {
		return autorRep.save(a);
	}

	public void delete(Long id) {
		if (!autorRep.existsById(id)) {
			throw new ResourceNotFoundException("Autor não encontrado");
		}
		autorRep.deleteById(id);
	}

	public List<Autor> findAll() {
		return autorRep.findAll();
	}

	public Autor findById(Long id) {
		return autorRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));
	}

	public Autor update(Long id, Autor autorAlt) {
		return autorRep.findById(id).map(autorDB -> {
			autorDB.setNome(autorAlt.getNome());
			autorDB.setNacionalidade(autorAlt.getNacionalidade());
			autorDB.setDataNascimento(autorAlt.getDataNascimento());
			return autorRep.save(autorDB);
		}).orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));
	}
}
