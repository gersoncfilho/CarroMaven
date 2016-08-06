package br.com.livro.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public BaseDAO(){
		try
		{
			//Necessário para utilizar o driver JDBC do MYSQL
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			//Erro de driver JDBC
			System.out.println("adicione o driver .jar do MYSQL em /WEB-INF/lib");
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() throws SQLException
	{
		//URL de conexao com o banco de dados
		String url = "jdbc:mysql://localhost/livro?useSSL=false";
		//Conecta usando url, usuario e senha
		Connection conn = DriverManager.getConnection(url,"livro","livro123");
		return conn;
	}
	
	public static void main(String[] args) throws SQLException
	{
		BaseDAO db = new BaseDAO();
		//Testa conexao
		Connection conn = db.getConnection();
		System.out.println(conn);
	}
}
