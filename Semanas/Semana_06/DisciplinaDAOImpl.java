package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.db.DB;
import model.entities.Disciplina;

public class DisciplinaDAOImpl implements DisciplinaDAO {

    private Connection conexao;

    public DisciplinaDAOImpl(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void insert(Disciplina obj) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conexao.prepareStatement(
                    "INSERT INTO disciplina (nomedisciplina, cargahoraria) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, obj.getNomedisciplina());
            pst.setInt(2, obj.getCargahoraria());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                obj.setIddisciplina(rs.getInt(1));
            }
            System.out.println("Disciplina cadastrada: " + obj.getNomedisciplina());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(pst);
            DB.fechaResultSet(rs);
        }
    }

    @Override
    public void update(Disciplina obj) {
        PreparedStatement pst = null;
        try {
            pst = conexao.prepareStatement("UPDATE disciplina SET nomedisciplina=?, cargahoraria=? WHERE iddisciplina=?");
            pst.setString(1, obj.getNomedisciplina());
            pst.setInt(2, obj.getCargahoraria());
            pst.setInt(3, obj.getIddisciplina());
            pst.executeUpdate();
            System.out.println("Disciplina atualizada: " + obj.getNomedisciplina());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(pst);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement pst = null;
        try {
            pst = conexao.prepareStatement("DELETE FROM disciplina WHERE iddisciplina=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Disciplina ID " + id + " removida.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(pst);
        }
    }

    @Override
    public Disciplina findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conexao.prepareStatement("SELECT * FROM disciplina WHERE iddisciplina=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                return new Disciplina(
                        rs.getInt("iddisciplina"),
                        rs.getString("nomedisciplina"),
                        rs.getInt("cargahoraria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(pst);
            DB.fechaResultSet(rs);
        }
        return null;
    }

    @Override
    public List<Disciplina> findAll() {
        List<Disciplina> lista = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conexao.prepareStatement("SELECT * FROM disciplina");
            rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(new Disciplina(
                        rs.getInt("iddisciplina"),
                        rs.getString("nomedisciplina"),
                        rs.getInt("cargahoraria")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(pst);
            DB.fechaResultSet(rs);
        }
        return lista;
    }
}
