package com.shirashop.productapi.service;

import java.util.ArrayList;
import java.util.List;

import com.shirashop.productapi.exceptions.EntityNotFoundException;
import com.shirashop.productapi.exceptions.InvalidEntityException;
import com.shirashop.productapi.repository.ProductRepository;
import com.shirashop.productapi.repository.SubcategoriaRepository;
import com.shirashop.productapi.validations.ValidaSubcategoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirashop.productapi.dto.ProductDTO;
import com.shirashop.productapi.dto.ProdutosPorSubcategoria;
import com.shirashop.productapi.entities.Product;
import com.shirashop.productapi.entities.Subcategoria;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubcategoriaService {
	
	@Autowired
    SubcategoriaRepository subcategoriaRepository;
	
	@Autowired
    ProductRepository productRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CategoriaService categoriaService;
	
	// **** MÉTODOS DE CONSULTA ****
	
	/**
	 * Busca todas as subcategorias registradas
	 * @return retorna uma lista das subcategorias
	 */
	public List<Subcategoria> findAll(){
		log.info("[SubcategoriaService] : in findAll");
		
		List<Subcategoria> subcategorias = (List<Subcategoria>) subcategoriaRepository.findAll();
		
		log.info("[SubcategoriaService] : end findAll");
		return subcategorias;
	}
	
	/**
	 * Busca subcategoria no banco de dados pelo id
	 * @param id_subcategoria Id da subcategoria a ser pesquisada
	 * @return retorna o objeto encontrado, caso não encontre retorna null
	 */
	public Subcategoria findById(Integer id_subcategoria) {
		log.info("[SubcategoriaService] : in findById");
		
		if (id_subcategoria == null) {
			throw new EntityNotFoundException(
					"Desculpe, não foi possível encontrar uma subcategoria com esse id. \r\n"
					+ "Verifique e tente novamente.");
		}
		
		Subcategoria subcategoria = subcategoriaRepository.findById(id_subcategoria).orElseThrow(
				() -> new EntityNotFoundException(
						"Desculpe, não foi possível encontrar uma subcategoria com esse id. \r\n"
						+ "Verifique e tente novamente."));
		
		log.info("[SubcategoriaService] : end findById");
		return subcategoria;
	}

	public ProdutosPorSubcategoria findSubcategoriaWithProducts(Integer id_subcategoria) {
		log.info("[SubcategoriaService] : in findSubcategoriaWithProducts");
		
		Subcategoria subcategoriaDb = findById(id_subcategoria);
		
		List<Product> produtos = productRepository.findBySubcategoria(subcategoriaDb);
		List<ProductDTO> produtosDTO = new ArrayList<>();
		
		if (produtos != null) {
			for (Product produto: produtos) {

				ProductDTO produtoDTO = modelMapper.map(produto, ProductDTO.class);
				produtosDTO.add(produtoDTO);
				
			}			
		}
		
		ProdutosPorSubcategoria subcategEProds = new ProdutosPorSubcategoria();
		subcategEProds.setSubcategoria(subcategoriaDb);
		subcategEProds.setProdutos(produtosDTO);
		
		log.info("[SubcategoriaService] : end findSubcategoriaWithProducts");
		return subcategEProds;
	}
	
	// **** MÉTODOS CREATE ****
	
	/**
	 * Realiza o registro no banco de dados
	 * @param subcategoria Recebe o objeto subcategoria a ser registrado
	 */
	public void insertSubcategoria(Subcategoria subcategoria) {
		log.info("[SubcategoriaService] : in insertSubcategoria");
		
		// Valida os campos de subcategoria
		ValidaSubcategoria.isValidCreate(subcategoria);

		// Verifica se a categoria existe
		categoriaService.findById(subcategoria.getCategoria().getId_categoria());

		// Se encontrar a mesma subcategoria no banco de dados
		List<Subcategoria> subcategoriaDB = subcategoriaRepository.findByNomeSubcategoria(
										subcategoria.getNomeSubcategoria());
		
		if(subcategoriaDB !=null) {
			
			for (Subcategoria subcateg : subcategoriaDB) {
				if(subcateg.getCategoria().getId_categoria() == subcategoria.getCategoria().getId_categoria()) {
					
					throw new InvalidEntityException(
							"Esta subcategoria já existe.");
				}
				
			}
			
		}
		
		// Salva subcategoria
		subcategoriaRepository.save(subcategoria);
		
		log.info("[SubcategoriaService] : end insertSubcategoria");
	}
	
	// **** MÉTODOS UPDATE ****
	
	/**
	 * Realiza a atualização da subcategoria
	 * @param subcategoria Recebe o objeto a ser atualizado
	 */
	public void updateSubcategoria(Subcategoria subcategoria) {
		log.info("[SubcategoriaService] : in updateSubcategoria");
		
		// Verfica se existe a subcategoria
		Subcategoria subcategoriaDb = findById(subcategoria.getId_sub_categoria());
		
		// Valida os campos
		subcategoria = ValidaSubcategoria.isValidUpdate(subcategoriaDb, subcategoria);
		
		// Verifica se a categoria existe
		categoriaService.findById(subcategoria.getCategoria().getId_categoria());
		
		subcategoriaRepository.save(subcategoria);
		
		log.info("[SubcategoriaService] : end updateSubcategoria");
	}
	
	// **** MÉTODOS DELETE ****
	
	/**
	 * Deleta a subcategoria no banco de dados
	 * @param subcategoria Recebe o objeto a ser pesquisado e deletado
	 */
	public void deleteSubcategoria(Subcategoria subcategoria) {
		log.info("[SubcategoriaService] : in deleteSubcategoria");
		
		// Verifica se existe no banco de dados
		Subcategoria delSubcateg = findById(subcategoria.getId_sub_categoria());
		
		// Deletar a categoria registrada nos produtos
		List<Product> produtos = productRepository.findBySubcategoria(delSubcateg);
		for(Product produto : produtos) {
			produto.setSubcategoria(null);
		}
		
		subcategoriaRepository.delete(delSubcateg);
		
		log.info("[SubcategoriaService] : end deleteSubcategoria");
	}
	
}
