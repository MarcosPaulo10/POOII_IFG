package controller;

import java.util.List;

import entity.Editora;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EditoraController {

    private EntityManagerFactory emf;

    public EditoraController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void salvar(String nome, String cidade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Editora e = new Editora(null, nome, cidade);
            em.persist(e);
            em.getTransaction().commit();
            System.out.println("Editora cadastrada: " + e.getNome() + " (ID " + e.getId() + ")");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(Integer id, String nome, String cidade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Editora e = em.find(Editora.class, id);
            if (e == null) {
                System.out.println("Editora não encontrada.");
                return;
            }
            e.setNome(nome);
            e.setCidade(cidade);
            em.getTransaction().commit();
            System.out.println("Editora atualizada: " + e.getNome());
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
            Editora e = em.find(Editora.class, id);
            if (e == null) {
                System.out.println("Editora não encontrada.");
                return;
            }
            em.remove(e);
            em.getTransaction().commit();
            System.out.println("Editora ID " + id + " removida.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Editora buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Editora.class, id);
        } finally {
            em.close();
        }
    }

    public List<Editora> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Editora e", Editora.class).getResultList();
        } finally {
            em.close();
        }
    }
}
