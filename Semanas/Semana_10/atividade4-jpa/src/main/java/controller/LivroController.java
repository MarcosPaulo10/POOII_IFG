package controller;

import java.math.BigDecimal;
import java.util.List;

import entity.Autor;
import entity.Editora;
import entity.Livro;
import entity.TipoPublicacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class LivroController {

    private EntityManagerFactory emf;

    public LivroController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void salvar(String titulo, int anoPublicacao, String isbn, BigDecimal preco,
                       TipoPublicacao tipo, Integer editoraId, List<Integer> autorIds) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Editora editora = em.find(Editora.class, editoraId);
            if (editora == null) {
                System.out.println("Editora não encontrada.");
                em.getTransaction().rollback();
                return;
            }

            Livro livro = new Livro(null, titulo, anoPublicacao, isbn, preco, tipo, editora);

            for (Integer autorId : autorIds) {
                Autor autor = em.find(Autor.class, autorId);
                if (autor != null) {
                    livro.adicionarAutor(autor);
                }
            }

            em.persist(livro);
            em.getTransaction().commit();
            System.out.println("Livro cadastrado: " + livro.getTitulo() + " (ID " + livro.getId() + ")");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(Integer id, String titulo, int anoPublicacao, String isbn,
                          BigDecimal preco, TipoPublicacao tipo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Livro livro = em.find(Livro.class, id);
            if (livro == null) {
                System.out.println("Livro não encontrado.");
                return;
            }
            livro.setTitulo(titulo);
            livro.setAnoPublicacao(anoPublicacao);
            livro.setIsbn(isbn);
            livro.setPreco(preco);
            livro.setTipo(tipo);
            em.getTransaction().commit();
            System.out.println("Livro atualizado: " + livro.getTitulo());
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void excluir(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Livro livro = em.find(Livro.class, id);
            if (livro == null) {
                System.out.println("Livro não encontrado.");
                return;
            }
            em.remove(livro);
            em.getTransaction().commit();
            System.out.println("Livro ID " + id + " removido.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Livro buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Livro.class, id);
        } finally {
            em.close();
        }
    }

    public List<Livro> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
        } finally {
            em.close();
        }
    }
}
