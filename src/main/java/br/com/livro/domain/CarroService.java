package br.com.livro.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
		List<Carro> carros = db.getCarros();
		return carros;
	}

	//Busca carro por id
	public Carro getCarro(Long id)
	{
		return db.getCarroById(id);
	}

	//Deleta carro por id
	@Transactional(rollbackFor=Exception.class)
	public boolean delete(Long id)
	{
		return db.delete(id);
	}

	//Salva ou atualiza carro
	@Transactional(rollbackFor=Exception.class)
	public boolean save(Carro carro)
	{
		db.saveOrUpdate(carro);
		return true;
	}

	//Busca carro pelo nome
	public List<Carro> findByName(String name)
	{
		return db.findByName(name);
	}

	public List<Carro> findByTipo(String tipo){
		return findByTipo(tipo);
	}
}
