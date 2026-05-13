package controller;

import java.util.ArrayList;
import java.util.List;
import model.dao.CursoDAO;
import model.dao.FactoryDAO;
import model.entities.Curso;
import observer.CursoObserver;

public class CursoController {
    private CursoDAO cursoDAO;

    //lista de observadores de curso
    private List<CursoObserver> observers = new ArrayList<>();

    public CursoController(FactoryDAO factory) {
        this.cursoDAO = factory.createCursoDAO();
    }

    //método que registra o evento observado para Curso
    public void registrarObserver(CursoObserver observer) {
        observers.add(observer);
    }
    
    public void salvar(String nome) {
        Curso c = new Curso(null, nome);
        cursoDAO.insert(c);
        
        //notifica todos os observadores que um curso foi salvo
        for (CursoObserver obs : observers) {
            obs.onCursoCadastrado(c);
        }
    }

    public void atualizar(Integer id, String nome) {
        Curso c = new Curso(id, nome);
        cursoDAO.update(c);
    }

    public void excluir(Integer id) {
        cursoDAO.deleteById(id);
    }

    public Curso buscarPorId(Integer id) {
        return cursoDAO.findById(id);
    }

    public List<Curso> listarTodos() {
        return cursoDAO.findAll();
    }
}
