package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidade.Disciplina;

public class DisciplinaDAOImp implements DisciplinaDAO {

    private final Connection conexao;

    public DisciplinaDAOImp(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void insert(Disciplina obj) {
        if (obj == null) throw new IllegalArgumentException("Disciplina não pode ser nula");

        String sql = "INSERT INTO disciplina (nomedisciplina, cargahoraria) VALUES (?, ?)";
        try (PreparedStatement pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, obj.getNomedisciplina());
            pst.setInt(2, obj.getCargahoraria());
            
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setIdDisciplina(rs.getInt(1));
                    }
                }
                System.out.println("Disciplina inserida com sucesso! ID: " + obj.getIdDisciplina());
            } else {
                throw new RuntimeException("Erro inesperado: Nenhuma linha foi inserida.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir disciplina", e);
        }
    }

    @Override
    public void update(Disciplina obj) {
        if (obj == null || obj.getIdDisciplina() == null) {
            throw new IllegalArgumentException("Disciplina e ID não podem ser nulos");
        }

        String sql = "UPDATE disciplina SET nomedisciplina = ?, cargahoraria = ? WHERE idDisciplina = ?";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, obj.getNomedisciplina());
            pst.setInt(2, obj.getCargahoraria());
            pst.setInt(3, obj.getIdDisciplina());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Disciplina com ID " + obj.getIdDisciplina() + " não encontrada.");
            }
            System.out.println("Disciplina atualizada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar disciplina", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo");

        String sql = "DELETE FROM disciplina WHERE idDisciplina = ?";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Disciplina com ID " + id + " não encontrada para exclusão.");
            }
            System.out.println("Disciplina com ID " + id + " removida com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir disciplina", e);
        }
    }

    @Override
    public Disciplina findById(Integer id) {
        if (id == null) return null;

        String sql = "SELECT * FROM disciplina WHERE idDisciplina = ?";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Disciplina(
                        rs.getInt("idDisciplina"),
                        rs.getString("nomedisciplina"),
                        rs.getInt("cargahoraria")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar disciplina por ID", e);
        }
        return null;
    }

    @Override
    public List<Disciplina> findAll() {
        List<Disciplina> lista = new ArrayList<>();
        String sql = "SELECT * FROM disciplina ORDER BY nomedisciplina";
        try (PreparedStatement pst = conexao.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                lista.add(new Disciplina(
                    rs.getInt("idDisciplina"),
                    rs.getString("nomedisciplina"),
                    rs.getInt("cargahoraria")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todas as disciplinas", e);
        }
        return lista;
    }
}
