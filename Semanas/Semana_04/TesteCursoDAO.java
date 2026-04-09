package principal;

import entidade.Curso;
import dao.CursoDAOImp;
import jdbc.DB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TesteCursoDAO {

    public static void main(String[] args) {

        Connection conexao = null;
        CursoDAOImp cursoDAO = null;

        try {
            conexao = DB.getConexao();
            cursoDAO = new CursoDAOImp(conexao);

            try (Statement stmt = conexao.createStatement()) {
                stmt.executeUpdate("TRUNCATE TABLE curso RESTART IDENTITY");
                System.out.println("Tabela truncada e sequência reiniciada com sucesso.");
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao truncar tabela curso", e);
            }

            System.out.println("===== TESTE DO CURSO DAO =====\n");

            // 2. TESTE insert
            System.out.println("--- Inserindo cursos ---");
            Curso curso1 = new Curso(null, "Engenharia de Software");
            Curso curso2 = new Curso(null, "Análise de Sistemas");
            Curso curso3 = new Curso(null, "Ciência da Computação");

            cursoDAO.insert(curso1);
            cursoDAO.insert(curso2);
            cursoDAO.insert(curso3);

            System.out.println();

            // 3. TESTE findAll
            System.out.println("--- Listando todos os cursos ---");
            List<Curso> cursos = cursoDAO.findAll();
            for (Curso curso : cursos) {
                System.out.println(curso.getIdCurso() + " - " + curso.getNomeCurso());
            }
            System.out.println();

            // 4. TESTE findById
            System.out.println("--- Buscando curso por ID (2) ---");
            Curso c = cursoDAO.findById(2);
            if (c != null) {
                System.out.println(c.getIdCurso() + " - " + c.getNomeCurso());
            } else {
                System.out.println("Curso com ID 2 não encontrado.");
            }
            System.out.println();

            // 5. TESTE update
            System.out.println("--- Atualizando curso ID 2 para 'Sistemas de Informação' ---");
            curso2.setNomeCurso("Sistemas de Informação");
            cursoDAO.update(curso2);
            System.out.println();

            // 6. TESTE deleteById
            System.out.println("--- Removendo curso ID 3 ---");
            cursoDAO.deleteById(3);

            System.out.println("--- Lista após remoção ---");
            List<Curso> encontrados = cursoDAO.findAll();
            for (Curso curso : encontrados) {
                System.out.println(curso.getIdCurso() + " - " + curso.getNomeCurso());
            }
            System.out.println();

            System.out.println("--- Tentando buscar ID 3 ---");
            Curso removido = cursoDAO.findById(3);
            System.out.println("Resultado: " + (removido == null ? "null (não encontrado)" : removido.getNomeCurso()));
            System.out.println();

        } catch (Exception e) {
            System.err.println("Erro durante os testes: " + e.getMessage());
            e.printStackTrace();
        } finally {
           DB.fechaConexao();
        }
    }
}