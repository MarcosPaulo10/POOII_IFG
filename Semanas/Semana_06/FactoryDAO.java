package model.dao;

public interface FactoryDAO {
    AlunoDAO createAlunoDAO();
    CursoDAO createCursoDAO();
    DisciplinaDAO createDisciplinaDAO();
}
