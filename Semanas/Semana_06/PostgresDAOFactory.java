package model.dao;

import model.db.DB;

public class PostgresDAOFactory implements FactoryDAO {

    @Override
    public AlunoDAO createAlunoDAO() {
        return new AlunoDAOImpl(DB.getConexao());
    }

    @Override
    public CursoDAO createCursoDAO() {
        return new CursoDAOImpl(DB.getConexao());
    }

    @Override
    public DisciplinaDAO createDisciplinaDAO() {
        return new DisciplinaDAOImpl(DB.getConexao());
    }
}
