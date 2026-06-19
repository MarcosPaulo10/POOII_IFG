package br.edu.suainstituicao.biblioteca.services;

import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.suainstituicao.biblioteca.entities.Autor;
import br.edu.suainstituicao.biblioteca.entities.Livro;
import br.edu.suainstituicao.biblioteca.repositories.AutorRepository;
import br.edu.suainstituicao.biblioteca.repositories.LivroRepository;
import br.edu.suainstituicao.biblioteca.services.exceptions.ResourceNotFoundException;

@Service
public class LivroService {

	@Autowired
	LivroRepository livroRep;

	@Autowired
	AutorRepository autorRep;

	public Livro insert(Livro l) {
		validarLivro(l);
		l.setAutor(buscarAutor(l.getAutor().getId()));
		if (livroRep.existsByIsbn(l.getIsbn())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN já cadastrado");
		}
		return livroRep.save(l);
	}

	public void delete(Long id) {
		if (!livroRep.existsById(id)) {
			throw new ResourceNotFoundException("Livro não encontrado");
		}
		livroRep.deleteById(id);
	}

	public List<Livro> findAll() {
		return livroRep.findAll();
	}

	public Livro findById(Long id) {
		return livroRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
	}

	public List<Livro> findByAutorId(Long autorId) {
		if (!autorRep.existsById(autorId)) {
			throw new ResourceNotFoundException("Autor não encontrado");
		}
		return livroRep.findByAutorId(autorId);
	}

	public Livro update(Long id, Livro livroAlt) {
		validarLivro(livroAlt);
		return livroRep.findById(id).map(livroDB -> {
			if (!livroDB.getIsbn().equals(livroAlt.getIsbn()) && livroRep.existsByIsbn(livroAlt.getIsbn())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN já cadastrado");
			}
			livroDB.setTitulo(livroAlt.getTitulo());
			livroDB.setIsbn(livroAlt.getIsbn());
			livroDB.setAnoPublicacao(livroAlt.getAnoPublicacao());
			livroDB.setNumeroPaginas(livroAlt.getNumeroPaginas());
			livroDB.setAutor(buscarAutor(livroAlt.getAutor().getId()));
			return livroRep.save(livroDB);
		}).orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
	}

	private Autor buscarAutor(Long id) {
		return autorRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));
	}

	private void validarLivro(Livro l) {
		if (l.getAutor() == null || l.getAutor().getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autor é obrigatório");
		}
		if (l.getNumeroPaginas() == null || l.getNumeroPaginas() <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de páginas deve ser maior que zero");
		}
		if (l.getAnoPublicacao() == null || l.getAnoPublicacao() > Year.now().getValue()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ano de publicação inválido");
		}
	}
}
