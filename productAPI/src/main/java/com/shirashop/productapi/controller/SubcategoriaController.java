package com.shirashop.productapi.controller;

import java.util.List;

import com.shirashop.productapi.exceptions.EntityNotFoundException;
import com.shirashop.productapi.service.SubcategoriaService;
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

import com.shirashop.productapi.dto.ProdutosPorSubcategoria;
import com.shirashop.productapi.entities.Subcategoria;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/product/subcategoria")
@Slf4j
public class SubcategoriaController {
	
	@Autowired
    SubcategoriaService subcategoriaService;

	// **** MÉTODOS DE CONSULTA ****
	
	// Consulta todas as subcategorias
	@GetMapping(path="/all")
	public ResponseEntity<List<Subcategoria>> findAll(){
		log.info("[SubcategoriaController] : in findAll");
		
		List<Subcategoria> subcategorias = subcategoriaService.findAll();
		
		if (subcategorias.size() == 0) {
			throw new EntityNotFoundException(
					"Ops! Ainda não há sub_categorias cadastradas");
		}
		
		log.info("[SubcategoriaController] : end findAll");
		return new ResponseEntity<List<Subcategoria>>(subcategorias, HttpStatus.OK);
	}
	
	// Consulta subcategoria por id
	@GetMapping(path="/id/{id_sub_categoria}")
	public ResponseEntity<ProdutosPorSubcategoria> findBydId(@PathVariable("id_sub_categoria") Integer id_sub_categoria){
		log.info("[SubcategoriaController] : in findById");
		
		ProdutosPorSubcategoria subcategoriaProd = subcategoriaService.findSubcategoriaWithProducts(id_sub_categoria);
		
		log.info("[SubcategoriaController] : end findById");
		return new ResponseEntity<ProdutosPorSubcategoria>(subcategoriaProd, HttpStatus.OK);
	}
	
	
	// **** MÉTODOS CREATE ****
	
	// Insere uma subcategoria
	@PostMapping(path="/register")
	public ResponseEntity<String> insertSubcategoria(@RequestBody Subcategoria subcategoria) {
		log.info("[SubcategoriaController] : in insertSubcategoria");
		
		subcategoriaService.insertSubcategoria(subcategoria);
		
		log.info("[SubcategoriaController] : end insertSubcategoria");
		return new ResponseEntity<String>("Subcategoria adicionada com sucesso!",HttpStatus.OK);
	}
	
	// **** MÉTODOS UPDATE ****
	
	// Atualiza uma subcategoria
	@PutMapping(path="/update")
	public ResponseEntity<String> updateSubcategoria(@RequestBody Subcategoria subcategoria) {
		log.info("[SubcategoriaController] : in updateSubcategoria");
		
		subcategoriaService.updateSubcategoria(subcategoria);
		
		log.info("[SubcategoriaController] : end updateSubcategoria");
		return new ResponseEntity<String>("Subcategoria atualizada com sucesso!", HttpStatus.OK);
	}
	
	// **** MÉTODOS DELETE ****
	
	// Deleta uma subcategoria
	@DeleteMapping(path="/delete")
	public ResponseEntity<String> deleteSubcategoria(@RequestBody Subcategoria subcategoria) {
		log.info("[SubcategoriaController] : in deleteSubcategoria");
		
		subcategoriaService.deleteSubcategoria(subcategoria);
		
		log.info("[SubcategoriaController] : end deleteSubcategoria");
		return new ResponseEntity<String>("Subcategoria deletada com sucesso!",HttpStatus.OK);
	}
	
}