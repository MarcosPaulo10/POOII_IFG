package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Aluno;

public class AlunoJDBC {

	public static boolean salvar(Aluno a) throws SQLException {
		
		String sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES ( ?, ?, ?)";
		Connection cnx = DB.getConexao();
		PreparedStatement pst = null;
		try {
			pst = cnx.prepareStatement(sql);
			
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			pst.setDate(3, a.getDt_nasc());
			
			return pst.executeUpdate() >= 1;
		
		} finally {
			pst.close();
		}
	}

	public static boolean apagar(int id) throws SQLException {
		
		String sql = "DELETE FROM aluno WHERE id = ?";
		Connection cnx = DB.getConexao();
		PreparedStatement pst = null;
		
		try {
			pst = cnx.prepareStatement(sql);
			
			pst.setInt(1, id);
			
			return pst.executeUpdate() >= 1;	
		
		} finally {
			pst.close();
		}
	}

	public static boolean alterar(Aluno a) throws SQLException {
		
		String sql = "UPDATE aluno SET nome=?, sexo=?, dt_nasc=? WHERE id = ?";
		Connection cnx = DB.getConexao();
		PreparedStatement pst = null;
		
		try {
			
			pst = cnx.prepareStatement(sql);
			
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			pst.setDate(3, a.getDt_nasc());
			pst.setInt(4, a.getId());
			
			return pst.executeUpdate() >= 1;
			
		} finally {
			pst.close();
		}
		
	}

	public static List<Aluno> listar() throws SQLException {
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		String sql = "SELECT * FROM aluno ORDER BY id";
		Connection cnx = DB.getConexao();
		Statement st = null;
		ResultSet rs = null;
		
		try {
		
			st = cnx.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				alunos.add(new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("sexo"), rs.getDate("dt_nasc")));
			}
			return alunos;
			
		} finally {
			st.close();
			rs.close();
		}
	}
}