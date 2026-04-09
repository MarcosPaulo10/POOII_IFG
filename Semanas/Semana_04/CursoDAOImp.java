package dao;

import entidade.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOImp implements CursoDAO {

    private final Connection conexao;

    public CursoDAOImp(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void insert(Curso c) {
    	
        if (c == null) 
        		throw new IllegalArgumentException("Curso não pode ser nulo");

        if (c.getNomeCurso() == null || c.getNomeCurso().trim().isEmpty())
            throw new IllegalArgumentException("Nome do curso não pode ser vazio");
        
        String sql = "INSERT INTO curso (nomecurso) VALUES (?)";
        try (PreparedStatement pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, c.getNomeCurso());
            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        c.setIdCurso(rs.getInt(1));
                    }
                }
                System.out.println("Curso inserido: " + c.getIdCurso() + " | " + c.getNomeCurso());
            } else {
                throw new RuntimeException("Nenhuma linha inserida para o curso: " + c.getNomeCurso());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir curso", e);
        }
    }

    @Override
    public void update(Curso c) {
    	
    		if (c == null || c.getIdCurso() == null)
            throw new IllegalArgumentException("Curso e ID não podem ser nulos");

        String sql = "UPDATE curso SET nomecurso = ? WHERE idcurso = ?";
        
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
           
        		pst.setString(1, c.getNomeCurso());
            pst.setInt(2, c.getIdCurso());
            int linhas = pst.executeUpdate();

            if (linhas == 0) {
                throw new RuntimeException("Curso com ID " + c.getIdCurso() + " não encontrado para atualização");
            }
            
            System.out.println("Curso atualizado: " + c.getIdCurso() + " | " + c.getNomeCurso());
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar curso", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
    	
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo");

        String sql = "DELETE FROM curso WHERE idcurso = ?";
        
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
        
        		pst.setInt(1, id);
            int linhas = pst.executeUpdate();
        
            if (linhas == 0) {
                throw new RuntimeException("Curso com ID " + id + " não existe");
            }
            
            System.out.println("Curso removido: ID " + id);
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar curso", e);
        }
    }

    @Override
    public Curso findById(Integer id) {
        
    		if (id == null) return null;

        String sql = "SELECT idcurso, nomecurso FROM curso WHERE idcurso = ?";
        
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            
        		pst.setInt(1, id);
        
            try (ResultSet rs = pst.executeQuery()) {
            
            		if (rs.next()) {
                    return new Curso(rs.getInt("idcurso"), rs.getString("nomecurso"));
                }
                return null;
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar curso por ID", e);
        }
    }

    @Override
    public List<Curso> findAll() {
        
    		List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT idcurso, nomecurso FROM curso";
        
        try (PreparedStatement pst = conexao.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()
        		) {
            
        		while (rs.next()) {
                cursos.add(new Curso(rs.getInt("idcurso"), rs.getString("nomecurso")));
            }
            
        		return cursos;
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os cursos", e);
        }
    }
}