package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import entity.Aluno;
import entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public final class App {

	public static void main(String[] args) throws ParseException {
		
		System.out.println("\n*** Aula 2 ***");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("academico");
		EntityManager em = emf.createEntityManager();

		// Inicia o controle de transação com o banco através do EntityManager
		em.getTransaction().begin();

		DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
		Aluno a1 = new Aluno(null, "Isabelle", "Feminino", df.parse("01-01-2010"));

		Curso c1 = new Curso(null, "POO II");

		c1.adicionarAluno(a1);

		// Grava os objetos no banco - cada objeto vira uma linha da respectiva tabela
		em.persist(a1);
		em.persist(c1);

		// Finaliza a transação dando commit no banco
		em.getTransaction().commit();

		TypedQuery<Curso> query1 = em.createQuery("SELECT c FROM Curso c", Curso.class);
		List<Curso> cursos = query1.getResultList();
		
		for (Curso c : cursos) {
			System.out.println("\n *** [" + c.getIdcurso() + " | " + c.getNomecurso() + "] ***");
			for (Aluno a : c.getAlunos()) {
				System.out.println("\tAluno: " + a.getIdaluno() + " | " + a.getNome());
			}

		}

		TypedQuery<Aluno> query2 = em.createQuery("SELECT a FROM Aluno a", Aluno.class);

		List<Aluno> alunos = query2.getResultList();
		for (Aluno a : alunos) {
			System.out.println("\n *** [" + a.getIdaluno() + " | " + a.getNome() + "] ***");
			for (Curso c : a.getCursos()) {
				System.out.println("\tCurso: " + c.getIdcurso() + " | " + c.getNomecurso());
			}
		}

		em.close();
		emf.close();
	}
}
