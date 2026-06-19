package br.edu.suainstituicao.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.edu.suainstituicao.biblioteca.repositories.AutorRepository;
import br.edu.suainstituicao.biblioteca.repositories.LivroRepository;

@Configuration
public class Config implements CommandLineRunner {

	@Autowired
	private AutorRepository autorRep;

	@Autowired
	private LivroRepository livroRep;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Qtde Autores: " + autorRep.count());
		System.out.println("Qtde Livros: " + livroRep.count());

	}
}
