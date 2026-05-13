package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.db.DB;
import model.entities.Curso;

public class CursoDAOImpl implements CursoDAO {

    private Connection conexao;

    public CursoDAOImpl(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void insert(Curso obj) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conexao.prepareStatement("INSERT INTO curso (nomecurso) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, obj.getNomecurso());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                obj.setIdcurso(rs.getInt(1));
            }
            System.out.println("Curso cadastrado: " + obj.getNomecurso());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(pst);
            DB.fechaResultSet(rs);
        }
    }

    @Override
    public void update(Curso obj) {
        PreparedStatement pst = null;
        try {
            pst = conexao.prepareStatement("UPDATE curso SET nomecurso=? WHERE idcurso=?");
            pst.setString(1, obj.getNomecurso());
            pst.setInt(2, obj.getIdcurso());
            pst.executeUpdate();
            System.out.println("Curso atualizado: " + obj.getNomecurso());
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
            pst = conexao.prepareStatement("DELETE FROM curso WHERE idcurso=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Curso ID " + id + " removido.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fechaPreparedStatement(pst);
        }
    }

    @Override
    public Curso findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conexao.prepareStatement("SELECT * FROM curso WHERE idcurso=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                return new Curso(rs.getInt("idcurso"), rs.getString("nomecurso"));
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
    public List<Curso> findAll() {
        List<Curso> lista = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conexao.prepareStatement("SELECT * FROM curso");
            rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(new Curso(rs.getInt("idcurso"), rs.getString("nomecurso")));
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
