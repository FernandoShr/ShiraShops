package com.shirashop.productapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirashop.productapi.entities.Categoria;
import com.shirashop.productapi.entities.Subcategoria;

@Repository
public interface SubcategoriaRepository extends CrudRepository<Subcategoria, Integer>{

	public List<Subcategoria> findByCategoria(Categoria categoria);
	
	public List<Subcategoria> findByNomeSubcategoria(String nomeSubcategoria);
	
}
