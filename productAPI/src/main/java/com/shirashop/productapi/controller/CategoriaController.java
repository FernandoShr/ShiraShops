package com.shirashop.productapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shirashop.productapi.dto.ProdutosPorCategoria;
import com.shirashop.productapi.entities.Categoria;
import com.shirashop.productapi.exceptions.EntityNotFoundException;
import com.shirashop.productapi.service.CategoriaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product/categoria")
@Slf4j
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	// **** MÉTODOS DE CONSULTA ****
	// Consulta TODOS
	@GetMapping(path="/all")
	public ResponseEntity<List<Categoria>> findAll(){
		log.info("[CategoriaController] : in findAll");
		
		List<Categoria> categorias = categoriaService.findAll();
		
		if (categorias.size() == 0) {
			throw new EntityNotFoundException(
					"Ops! Ainda não há categorias cadastradas");
		}
		
		log.info("[CategoriaController] : end findAll");
		return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK);
	}
	
	// Consulta por ID
	@GetMapping(path="/id/{id_categoria}")
	public ResponseEntity<ProdutosPorCategoria> findById(@PathVariable("id_categoria") Integer id_categoria){
		log.info("[CategoriaController] : in findById");
		
		ProdutosPorCategoria prodCateg = categoriaService.findCategoriaWithProducts(id_categoria);
		
		log.info("[CategoriaController] : end findById");
		return new ResponseEntity<ProdutosPorCategoria>(prodCateg, HttpStatus.OK);
	}
	
	
	// **** MÉTODOS DE CREATE ****
	
	@PostMapping(path="/register")
	public ResponseEntity<String> insertCategoria(@RequestBody Categoria categoria) {
		log.info("[CategoriaController] : in insertCategoria");
		
		categoriaService.insertCategoria(categoria);
		
		log.info("[CategoriaController] : end insertCategoria");
		return new ResponseEntity<String>("Categoria adicionada com sucesso", HttpStatus.OK);
	}
	
	// **** MÉTODOS DE UPDATE ****
	
	@PutMapping(path="/update")
	public ResponseEntity<String> updateCategoria(@RequestBody Categoria categoria) {
		log.info("[CategoriaController] : in updateCategoria");
		
		categoriaService.updateCategoria(categoria);
		
		log.info("[CategoriaController] : end updateCategoria");
		return new ResponseEntity<String>("Categoria atualizada com sucesso." ,HttpStatus.OK);
	}
	
	
	// **** MÉTODOS DE DELETE ****
	
	@DeleteMapping(path="/delete")
	public ResponseEntity<String> deleteCategoria(@RequestBody Categoria categoria) {
		log.info("[CategoriaController] : in deleteCategoria");
		
		categoriaService.deleteCategoria(categoria);
		
		log.info("[CategoriaController] : end deleteCategoria");
		return new ResponseEntity<String>("Categoria deletada com sucesso", HttpStatus.OK);
	}
}
