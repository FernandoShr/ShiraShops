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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shirashop.productapi.dto.ProductOrder;
import com.shirashop.productapi.entities.Product;
import com.shirashop.productapi.exceptions.EntityNotFoundException;
import com.shirashop.productapi.service.ProductService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// **** MÉTODOS DE CONSULTA ****
	
	// Consulta todos os produtos cadastrados
	@GetMapping(path = "/all")
	public ResponseEntity<List<Product>> consultingAll() {
		log.info("[ProductController] : in consultingAll");

		List<Product> produtos = productService.findAll();
		
		if(produtos.size() == 0) {
			throw new EntityNotFoundException(
					"Não há produtos cadastrados");
		}
		
		log.info("[ProductController] : end consultingAll");
		return new ResponseEntity<List<Product>>(produtos, HttpStatus.OK);
	}
	
	// Consulta único produto pelo id
	@GetMapping(path = "/id/{id_produto}")
	public ResponseEntity<Product> consultingId(@PathVariable("id_produto") int id_produto) {
		log.info("[ProductController] : in consultingId");
		
		Product product = productService.findById(id_produto);
		
		log.info("[ProductController] : end consultingId");
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}

	// Consulta pelo nome
	@GetMapping(path="/nome/{nome}")
	public ResponseEntity<List<Product>> consultingByName(@PathVariable("nome") String nome) {
		log.info("[ProductController] : in consultingByName");
		
		List<Product> produtos = productService.findByNome(nome);
		
		log.info("[ProductController] : end consultingByName");
		return new ResponseEntity<List<Product>>(produtos, HttpStatus.OK);
	}
	
	
	// **** MÉTODOS DE CREATE ****
	
	@PostMapping(path="/register")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		log.info("[ProductController] : in saveProduct");
		
		productService.saveProduct(product);
		
		log.info("[ProductController] : end saveProduct");
		return new ResponseEntity<String>("Produto registrado com sucesso!", HttpStatus.OK);
	}
	
	
	// **** MÉTODOS DE UPDATE ****
	
	@PutMapping(path="/update")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		log.info("[ProductController] : in updateProduct");
		
		productService.updateProduct(product);
		
		log.info("[ProductController] : end updateProduct");
		return new ResponseEntity<String>("Produto atualizado com sucesso!", HttpStatus.OK);
	}
	
	@PutMapping(path="/subStock")
	public void subtraiEstoque(@RequestBody Product product,@RequestParam int quantidade) {
		log.info("[ProductController] : in subtraiEstoque");

		productService.subtraiEstoque(product, quantidade);
		
		log.info("[ProductController] : end subtraiEstoque");
	}
	
	@PutMapping(path="/returnStock")
	public void retornaEstoque(@RequestBody List<ProductOrder> products) {
		log.info("[ProductController] : in retornaEstoque");
		
		productService.retornaEstoque(products);
		
		log.info("[ProductController] : end retornaEstoque");
	}
	
	// **** MÉTODOS DE DELETE ****
	
	// Deleta Produto
	@DeleteMapping(path="/delete")
	public ResponseEntity<String> deleteProduct (@RequestBody Product product) {
		log.info("[ProductController] : in deleteProduct");
		
		productService.deleteProduct(product);			
		
		log.info("[ProductController] : end deleteProduct");
		return new ResponseEntity<String>("Produto deletado com sucesso!",HttpStatus.OK);
	}			
}
