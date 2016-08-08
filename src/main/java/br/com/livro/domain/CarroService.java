package br.com.livro.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarroService {
	
	//Antes do Spring
	//private CarroDAO db = new CarroDAO();
	
	
	//Depois do Spring
	@Autowired
	private CarroDAO db;
	
	//Lista todos os carros do banco de dados
	public List<Carro> getCarros()
	{
		try
		{
			List<Carro> carros = db.getCarros();
			return carros;
		}catch(SQLException e){
			e.printStackTrace();
			return new ArrayList<Carro>();
		}
	}
	
	//Busca carro por id
	public Carro getCarro(Long id)
	{
		try
		{
			return db.getCarroById(id);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Deleta carro por id
	
	public boolean delete(Long id)
	{
		try
		{
			return db.delete(id);
		}catch(SQLException e){
			return false;
		}
	}
	
	//Salva ou atualiza carro
	public boolean save(Carro carro)
	{
		try
		{
			db.save(carro);
			return true;
		}catch(SQLException e){
			return false;
		}
	}
	
	//Busca carro pelo nome
	public List<Carro> findByName(String name)
	{
		try
		{
			return db.findByName(name);
		}catch(SQLException e){
			return null;
		}
	}
	
	public List<Carro> findByTipo(String tipo){
		try
		{
			return db.findByTipo(tipo);
		}catch(SQLException e){
			return null;
		}
	}
}
