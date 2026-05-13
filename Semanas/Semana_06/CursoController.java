package controller;

import java.util.List;
import model.dao.CursoDAO;
import model.dao.FactoryDAO;
import model.entities.Curso;

public class CursoController {
    private CursoDAO cursoDAO;

    public CursoController(FactoryDAO factory) {
        this.cursoDAO = factory.createCursoDAO();
    }

    public void salvar(String nome) {
        Curso c = new Curso(null, nome);
        cursoDAO.insert(c);
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
