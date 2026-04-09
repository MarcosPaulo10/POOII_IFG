package principal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.DisciplinaDAO;
import dao.DisciplinaDAOImp;
import entidade.Disciplina;
import jdbc.DB;

public class TesteDisciplinaDAO {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DB.getConexao();
            DisciplinaDAO dao = new DisciplinaDAOImp(conn);

            
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("TRUNCATE TABLE disciplina RESTART IDENTITY");
                System.out.println("Tabela truncada para o início dos testes.");
            }

            System.out.println("\n--- Início do Teste Disciplina DAO ---");

            
            Disciplina d1 = new Disciplina("Programação Orientada a Objetos II", 80);
            Disciplina d2 = new Disciplina("Sistemas de Informação", 60);
            Disciplina d3 = new Disciplina("Estrutura de Dados", 40);

            dao.insert(d1);
            dao.insert(d2);
            dao.insert(d3);

            
            System.out.println("\nListando todas as disciplinas:");
            List<Disciplina> lista = dao.findAll();
            for (Disciplina d : lista) {
                System.out.println(d);
            }

            
            System.out.println("\nBuscando disciplina com ID 2:");
            Disciplina encontrada = dao.findById(2);
            System.out.println("Encontrada: " + encontrada);

            
            System.out.println("\nAtualizando disciplina ID 2:");
            if (encontrada != null) {
                encontrada.setNomedisciplina("Engenharia de Software");
                encontrada.setCargahoraria(90);
                dao.update(encontrada);
            }

            
            System.out.println("\nExcluindo disciplina ID 3:");
            dao.deleteById(3);

            
            System.out.println("\nListagem Final:");
            for (Disciplina d : dao.findAll()) {
                System.out.println(d);
            }

            System.out.println("\n--- Fim do Teste Disciplina DAO ---");

        } catch (SQLException e) {
            System.err.println("Erro de SQL nos testes: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DB.fechaConexao();
        }
    }
}
