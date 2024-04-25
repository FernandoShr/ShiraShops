package com.shirashop.productapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirashop.productapi.entities.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer>{
	
	public Categoria findByNomeCategoria(String nomeCategoria);
	
}
