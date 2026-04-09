package dao;

import java.util.List;

import entidade.Curso;

public interface CursoDAO {

	void         insert(Curso c);
	void         update(Curso c);
	void         deleteById(Integer id);
	Curso        findById(Integer id);
	List<Curso>  findAll();
	
}