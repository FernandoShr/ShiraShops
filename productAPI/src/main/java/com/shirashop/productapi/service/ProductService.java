package com.shirashop.productapi.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.shirashop.productapi.exceptions.EntityNotFoundException;
import com.shirashop.productapi.exceptions.InvalidEntityException;
import com.shirashop.productapi.exceptions.OutOfStockException;
import com.shirashop.productapi.repository.CategoriaRepository;
import com.shirashop.productapi.repository.ProductRepository;
import com.shirashop.productapi.repository.SubcategoriaRepository;
import com.shirashop.productapi.validations.ValidaProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shirashop.productapi.dto.ProductOrder;
import com.shirashop.productapi.entities.Product;
import com.shirashop.productapi.entities.Subcategoria;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private SubcategoriaRepository subcategoriaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// **** MÉTODOS DE CONSULTA ****
	
	/**
	 * Consulta todos os produtos cadastrados
	 * @return Retorna uma lista de objetos produto
	 */
	public List<Product> findAll() {
		log.info("[ProductService] : in findAll");
		
		List<Product> produtos = (List<Product>) productRepository.findAll();
		
		log.info("[ProductService] : end findAll");
		return produtos;
	}
	
	/**
	 * Encontra no banco de dados o produto com o respectivo id
	 * @param id_produto Id do produto procurado
	 * @return retorna o objeto encontrado
	 */
	public Product findById(Integer id_produto) {
		log.info("[ProductService] : in findById");
		
		if(id_produto == null) {
			throw new EntityNotFoundException(
					"Desculpe, não foi possível encontrar um produto com este id. "
					+ "Verifique e tente novamente.");
		}
		
		Product product =  productRepository.findById(id_produto).orElseThrow(
				()-> new EntityNotFoundException(
						"Desculpe, não foi possível encontrar um produto com este id. "
						+ "Verifique e tente novamente."));
		
		log.info("[ProductService] : end findById");
		return product;
	}
	
	// CONSULTA POR NOME
	/**
	 * Busca pelo nome do produto no banco de dados
	 * @param nome Nome do produto
	 * @return Retorna uma lista de produtos
	 */
	public List<Product> findByNome(String nome) {
		log.info("[ProductService] : in findByNome");
		
		// Se o nome não for válido
		if (!ValidaProduto.isNameValid(nome)) {
			throw new InvalidEntityException(
					"Nome do produto deve ter entre 2 e 45 caracteres");
		}
		
		List<Product> products = productRepository.findByNomeProdutoContaining(nome);
		
		// Se não for encontrado nenhum produto com este nome
		if(products.size() == 0) {
			throw new EntityNotFoundException(
					"Não foi possível encontrar um produto com este nome. "
					+ "Verifique e tente novamente.");
		}
		
		
		log.info("[ProductService] : end findByNome");
		return products;
	}
	
	
	// **** MÉTODOS DE CREATE ****
	
	/**
	 * Registra produto no banco de dados
	 * @param product Objeto Product
	 */
	public Product saveProduct(Product product) {
		log.info("[ProductService] : in saveProduct");
		
		// Valida os campos do produto
		ValidaProduto.isValidCreate(product);
		
		// **** VERIFICA SKU ****
		// Se já existe cadastrado
		if (productRepository.findBySku(product.getSku()) != null) {
			throw new InvalidEntityException(
					"Produto com esse SKU já existe");
		}
		
		// ***** SALVA A DATA ATUAL ********
		// Data de criação do Produto
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		product.setData_criacao(dtf.format(LocalDateTime.now()));
		
		// **** VERIFICA SUBCATEGORIA ****
		// Verifica se existe no banco de dados
		Subcategoria subcategoriaDb = subcategoriaRepository.findById(
				product.getSubcategoria().getId_sub_categoria()).orElseThrow(
						() -> new EntityNotFoundException(
								"Desculpe, esta subcategoria não existe. "
								+ "Verifique e tente novamente."));
		

		product.setSubcategoria(subcategoriaDb);
		// Produto a ser salvo no banco de dados
//		Product produtoSalvo = modelMapper.map(product, Product.class);
//		produtoSalvo.setCategoria(product.getSubcategoria().getCategoria());
		
		productRepository.save(product);
		
		log.info("[ProductService] : end saveProduct");
		return product;
	}
	
	// **** MÉTODOS DE UPDATE ****
	
	/**
	 * Atualiza o Produto registrado no banco de dados
	 * @param product Objeto Product
	 */
	public Product updateProduct(Product product) {
		log.info("[ProductService] : in updateProduct");
		
		// Verifica se existe produto no banco de dados
		Product productDb = findById(product.getIdProduto());

		// **** SUBCATEGORIA ****
		if(product.getSubcategoria() == null) {
			product.setSubcategoria(productDb.getSubcategoria());
		}

		else {
			
			if (product.getSubcategoria().getId_sub_categoria() == null) {
				throw new EntityNotFoundException(
						"Por favor, inserir id da subcategoria.");
			}
			else {
				product.setSubcategoria(subcategoriaRepository.findById(
					product.getSubcategoria().getId_sub_categoria()).orElseThrow(
							()-> new EntityNotFoundException(
									"Esta subcategoria não existe. "
								  + "Por favor, tente novamente")));
			}
			
		}
		
		// Valida os campos
		product = ValidaProduto.isValidUpdate(productDb, product);
		
		//Recupera Data de Criação
		product.setData_criacao(productDb.getData_criacao());
		
		
		productRepository.save(product);
		
		log.info("[ProductService] : end updateProduct");
		return product;
	}
	
	public Product subtraiEstoque(Product product, int quantidade) {
		log.info("Removing stock from product...");
		if(product.getEstoque() == 0) {
			throw new OutOfStockException(
					"O produto " + "'" + product.getNomeProduto() +"'" + 
							" não possui mais estoque.\n" + 
							"(Id do Produto: " + product.getIdProduto() +")");
		}		
		
		Integer estoqueFinal = product.getEstoque()- quantidade;
		
		if (estoqueFinal < 0) {
			throw new OutOfStockException(
					"O produto " + "'" + product.getNomeProduto() + "'" + 
					" não possui estoque suficiente!\n" +
					"(Id do Produto: " + product.getIdProduto() + ")\n" +
					"(Estoque disponível: " + product.getEstoque() + ")");
		}
		product.setEstoque(estoqueFinal);
		
		return productRepository.save(product);
	}
	
	@Transactional
	public void retornaEstoque(List<ProductOrder> prodOrder) {
		for(ProductOrder prod : prodOrder) {
			Product product = findById(prod.getIdProduto());
			
			log.info("Returning stock to product...");
			
			Integer estoqueFinal = product.getEstoque() + prod.getQuantidade();
			
			product.setEstoque(estoqueFinal);
			
			productRepository.save(product);
		}
		
	}
	
	// **** MÉTODOS DE DELETE ****
	
	/**
	 * Deleta produto registrado no banco de dados
	 * @param product Objeto Product
	 */
	public void deleteProduct(Product product) {
		log.info("[ProductService] : in deleteProduct");
		
		// Busca pelo produto no banco de dados
		Product delProduct = findById(product.getIdProduto());
		
		productRepository.delete(delProduct);
		
		log.info("[ProductService] : end deleteProduct");
	}

}
