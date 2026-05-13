package controller;

import java.util.List;
import model.dao.FactoryDAO;
import model.dao.DisciplinaDAO;
import model.entities.Disciplina;

public class DisciplinaController {
    private DisciplinaDAO disciplinaDAO;

    public DisciplinaController(FactoryDAO factory) {
        this.disciplinaDAO = factory.createDisciplinaDAO();
    }

    public void salvar(String nome, Integer cargaHoraria) {
        Disciplina d = new Disciplina(null, nome, cargaHoraria);
        disciplinaDAO.insert(d);
    }

    public void atualizar(Integer id, String nome, Integer cargaHoraria) {
        Disciplina d = new Disciplina(id, nome, cargaHoraria);
        disciplinaDAO.update(d);
    }

    public void excluir(Integer id) {
        disciplinaDAO.deleteById(id);
    }

    public Disciplina buscarPorId(Integer id) {
        return disciplinaDAO.findById(id);
    }

    public List<Disciplina> listarTodos() {
        return disciplinaDAO.findAll();
    }
}
