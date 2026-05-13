package controller;

import java.util.List;

import entity.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AutorController {

    private EntityManagerFactory emf;

    public AutorController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void salvar(String nome, String nacionalidade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Autor a = new Autor(null, nome, nacionalidade);
            em.persist(a);
            em.getTransaction().commit();
            System.out.println("Autor cadastrado: " + a.getNome() + " (ID " + a.getId() + ")");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(Integer id, String nome, String nacionalidade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Autor a = em.find(Autor.class, id);
            if (a == null) {
                System.out.println("Autor não encontrado.");
                return;
            }
            a.setNome(nome);
            a.setNacionalidade(nacionalidade);
            em.getTransaction().commit();
            System.out.println("Autor atualizado: " + a.getNome());
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
            Autor a = em.find(Autor.class, id);
            if (a == null) {
                System.out.println("Autor não encontrado.");
                return;
            }
            em.remove(a);
            em.getTransaction().commit();
            System.out.println("Autor ID " + id + " removido.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Autor buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Autor.class, id);
        } finally {
            em.close();
        }
    }

    public List<Autor> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
        } finally {
            em.close();
        }
    }
}
