package controller;

import java.util.Date;
import java.util.List;

import model.dao.AlunoDAO;
import model.dao.FactoryDAO;
import model.entities.Aluno;

public class AlunoController {
	
	private AlunoDAO alunoDAO;

	public AlunoController(FactoryDAO factory) {
		this.alunoDAO = factory.createAlunoDAO();
	}
	
	public void salvar(String nome, String sexo, Date dtNasc) {
		Aluno a = new Aluno(null, nome, sexo, dtNasc);
		this.alunoDAO.insert(a);
	}
	
	public void atualizar(Integer id, String nome, String sexo, Date dtNasc) {
		Aluno a = new Aluno(id, nome, sexo, dtNasc);
		this.alunoDAO.update(a);
	}
	
	public void excluir(Integer id) {
		this.alunoDAO.deleteById(id);
	}
	
	public Aluno buscarPorId(Integer id) {
		return this.alunoDAO.findById(id);
	}
	
	public List<Aluno> listarTodos(){
		return this.alunoDAO.findAll();
	}
	
	
}
