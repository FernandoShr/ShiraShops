package com.shirashop.productapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirashop.productapi.entities.Product;
import com.shirashop.productapi.entities.Subcategoria;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	public List<Product> findByNomeProdutoContaining(String nomeProduto);
	
	public Product findBySku (int sku);
		
	public List<Product> findBySubcategoria(Subcategoria subcategoria);
}
