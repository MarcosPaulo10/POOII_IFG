import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

public class TesteConexao {

	public static void main(String[] args) throws SQLException, IOException {
		
		Connection conexao = DB.getConexao();
		
		if  (conexao != null) {
			System.out.println("Conexão realizada com sucesso!");
			
			System.out.println("Lista dos Alunos");
			
			PreparedStatement pst = conexao.prepareStatement(
			"INSERT INTO aluno (nome, sexo, dt_nasc) VALUES  (?,?,?)"
			);
			pst.setString(1, "João");
			pst.setString(2, "Masculino");
			
			GregorianCalendar c = new GregorianCalendar(2026, 3, 18);
			pst.setDate(3, new Date(c.getTimeInMillis()));
			
			int linhas = pst.executeUpdate();
			System.out.println("O aluno foi inserido com sucesso! (" + linhas + ")"); 
					
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM aluno");
			while (rs.next()) {
				System.out.println(rs.getInt(1) 
						          + " " + rs.getString(2)
						          + " " + rs.getString(3)
						          + " " + rs.getDate(4));
			}
			
		}
		else {
			System.out.println("Conexão não realizada!");
		}
		
		if (DB.closeConnetion())
			System.out.println("Conexão fechada!");
		else 
			System.out.println("Conexão inexistente!");
	}

}