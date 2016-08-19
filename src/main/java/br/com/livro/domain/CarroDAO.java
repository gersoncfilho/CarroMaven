package br.com.livro.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import  java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class CarroDAO extends HibernateDAO<Carro> {
	
	
	public CarroDAO()
	{
		//Informa o tipo de entidade para o hibernate
		super(Carro.class);
	}
	
	public Carro getCarroById(Long id)
	{
		//Antes do Hibernate
		/*Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				Carro c = createCarro(rs);
				rs.close();
				return c;
			}
		}finally
		{
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}
		return null;*/
		
		//Depois do hibernate
		return super.get(id);
		
	}
	
	public List<Carro> findByName(String name)
	{
		//Antes do hibernate
		/*List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where lower(nome) like ?");
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				Carro c = createCarro(rs);
				carros.add(c);				
			}
			rs.close();
		}finally
		{
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}
		return carros;*/
		
		//Hibernate
		Query q = getSession().createQuery("from Carro where lower(name) like lower(?)");
		q.setString(0, "%" + name + "%");
		return q.list();
				
	}
	
	public List<Carro> findByTipo(String tipo)
	{
		//Antes do hibernate
		/*List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where tipo = ?");
			stmt.setString(1, tipo);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				Carro c = createCarro(rs);
				carros.add(c);				
			}
			rs.close();
		}finally
		{
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}
		return carros;*/
		
		Query q = getSession().createQuery("from Carro where tipo = ?");
		q.setString(0, tipo);
		List<Carro> carros = q.list();
		return carros;
	}
	
	public List<Carro> getCarros()
	{
		//Antes hibernate
		/*List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro");
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();
		}finally
		{
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}
		return carros;*/
				
		//Hibernate
		Query q = getSession().createQuery("from Carro");
		List<Carro> carros = q.list();
		return carros;
	}
	

	//Antes hibernate
	/*public Carro createCarro(ResultSet rs) throws SQLException
	{
		Carro c = new Carro();
		c.setId(rs.getLong("id"));
		c.setNome(rs.getString("nome"));
		c.setDesc(rs.getString("descricao"));
		c.setUrlFoto(rs.getString("urlFoto"));
		c.setUrlVideo(rs.getString("urlVideo"));
		c.setLatitude(rs.getString("latitude"));
		c.setLongitude(rs.getString("longitude"));
		c.setTipo(rs.getString("tipo"));
		return c;
	}*/
	
	public void save(Carro c)
	{
		//Antes hibernate
		/*Connection conn = null;
		PreparedStatement stmt = null;
		try
		{
			conn = getConnection();
			if(c.getId() == null)
			{
				stmt = conn.prepareStatement("insert into carro(nome, descricao, urlfoto, urlvideo, latitude, longitude, tipo) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			}else{
				stmt = conn.prepareStatement("update carro set nome=?, descricao=?, urlfoto=?, urlvideo=?, latitude=?, longitude=?, tipo=? where id=?");
			}
			stmt.setString(1,  c.getNome());
			stmt.setString(2,  c.getDesc());
			stmt.setString(3,  c.getUrlFoto());
			stmt.setString(4, c.getUrlVideo());
			stmt.setString(5, c.getLatitude());
			stmt.setString(6, c.getLongitude());
			stmt.setString(7, c.getTipo());
			if(c.getId() != null)
			{
				//Update
				stmt.setLong(8,  c.getId());
			}
			int count = stmt.executeUpdate();
			if(count == 0)
			{
				throw new SQLException("Erro ao inserir carro");
			}
			// se inseriu, ler o id auto incremento
			if(c.getId() == null)
			{
				Long id = getGeneratedId(stmt);
				c.setId(id);
			}
		}finally{
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}*/
		
		//hibernate
		super.save(c);
		
	}
	
	//id gerado com o campo auto incremento
		/*public static Long getGeneratedId(Statement stmt) throws SQLException
		{
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
			{
				Long id = rs.getLong(1);
				return id;
			}
			return 0L;
		}
		
		public boolean delete(long id) throws SQLException
		{
			Connection conn = null;
			PreparedStatement stmt = null;
			try
			{
				conn = getConnection();
				stmt = conn.prepareStatement("delete from carro where id=?");
				stmt.setLong(1, id);
				int count = stmt.executeUpdate();
				boolean ok = count >0;
				return ok;
			}finally{
				if(stmt != null)
				{
					stmt.close();
				}
				if(conn != null)
				{
					conn.close();
				}
			}
		}*/
	
	//Hibernate
	public boolean delete(long id)
	{
		Carro c = get(id);
		delete(c);
		return true;
	}
	
}
