package com.shirashop.productapi.service;

import java.util.ArrayList;
import java.util.List;

import com.shirashop.productapi.exceptions.EntityNotFoundException;
import com.shirashop.productapi.exceptions.InvalidEntityException;
import com.shirashop.productapi.repository.CategoriaRepository;
import com.shirashop.productapi.repository.ProductRepository;
import com.shirashop.productapi.repository.SubcategoriaRepository;
import com.shirashop.productapi.validations.ValidaCategoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirashop.productapi.dto.ProductDTO;
import com.shirashop.productapi.dto.ProdutosPorCategoria;
import com.shirashop.productapi.entities.Categoria;
import com.shirashop.productapi.entities.Product;
import com.shirashop.productapi.entities.Subcategoria;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private SubcategoriaRepository subcategoriaRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// **** MÉTODOS DE CONUSLTA ****

	/**
	 * Busca todas as categorias registradas
	 * @return retorna uma Lista com as categorias registradas
	 */
	public List<Categoria> findAll() {
		log.info("[CategoriaService] : in findAll");
		
		List<Categoria> categorias = (List<Categoria>)categoriaRepository.findAll();
		
		log.info("[CategoriaService] : end findAll");
		return categorias;
	}
	
	/**
	 * Busca categoria no banco de dados pelo id
	 * @param id_categoria número do id da categoria a ser procurada
	 * @return retornar o objeto encontrado
	 */
	public Categoria findById(Integer id_categoria) {
		log.info("[CategoriaService] : in findById");
		
		if (id_categoria == null) {
			throw new EntityNotFoundException(
					"Desculpe, não foi possível encontrar uma categoria com esse id. \r\n"
					+ "Verifique e tente novamente.");
		}
		
		Categoria categoria = categoriaRepository.findById(id_categoria).orElseThrow(
				() -> new EntityNotFoundException(
						"Desculpe, não foi possível encontrar uma categoria com esse id. \r\n"
						+ "Verifique e tente novamente."));
		
		log.info("[CategoriaService] : end findById");
		return categoria;
	}
	
	/**
	 * Busca por uma Categoria e os Produtos que pertencem a essa Categoria
	 * @param id_categoria Id da Categoria
	 * @return Uma Objeto com a Categoria e uma Lista de Produtos
	 */
	public ProdutosPorCategoria findCategoriaWithProducts(Integer id_categoria) {
		log.info("[CategoriaService] : in findCategoriaWithProducts");
		
		Categoria categoriaDb = findById(id_categoria);
		
		// Reúne os produtos com a categoria selecionada
		List<Product> produtos = (List<Product>) productRepository.findAll();
		List<Product> produtosCateg = new ArrayList<>();
		for(Product produto : produtos) {
			if(categoriaDb == produto.getSubcategoria().getCategoria()) {
				produtosCateg.add(produto);
			}
		}
		
		List<ProductDTO> produtosDTO = new ArrayList<>();
		
		if (produtosCateg != null) {
			for (Product produto: produtosCateg) {

				ProductDTO produtoDTO = modelMapper.map(produto, ProductDTO.class);
				produtosDTO.add(produtoDTO);
				
			}			
		}
		
		ProdutosPorCategoria categEProds = new ProdutosPorCategoria();
		categEProds.setCategoria(categoriaDb);
		categEProds.setProdutos(produtosDTO);
		
		log.info("[CategoriaService] : end findCategoriaWithProducts");
		return categEProds;
	}
	
	// **** MÉTODOS CREATE ****
	
	/**
	 * Registra a categoria, caso já não esteja, no banco de dados
	 * @param categoria Objeto Categoria
	 */
	public void insertCategoria(Categoria categoria) {
		log.info("[CategoriaService] : in insertCategoria");
		
		// Valida Categoria
		ValidaCategoria.isValidCreate(categoria);
		
		// Se encontrar a mesma categoria no banco de dados
		if(categoriaRepository.findByNomeCategoria(categoria.getNomeCategoria())!=null) {
			throw new InvalidEntityException(
					"Esta categoria já existe.");
		}
		// Salva Categoria
		categoriaRepository.save(categoria);
		
		log.info("[CategoriaService] : end insertCategoria");
	}
	
	// **** MÉTODOS DE UPDATE ****
	
	/**
	 * Atualiza Categoria no banco de dados
	 * @param categoria Objeto Categoria
	 */
	public void updateCategoria(Categoria categoria) {
		log.info("[CategoriaService] : in updateCategoria");
		
		// Categoria encontrada no banco de dados
		findById(categoria.getId_categoria());
		
		// Valida os campos da Categoria
		ValidaCategoria.isValidUpdate(categoria);
		
		//Verifica se já não existe outra igual
		if (categoriaRepository.findByNomeCategoria(categoria.getNomeCategoria()) != null) {
			throw new InvalidEntityException("Esta Categoria já existe. "
											+ "Por favor, tente novamente.");
		}
		
		// Atualiza Categoria
		categoriaRepository.save(categoria);
		
		log.info("[CategoriaService] : end updateCategoria");
	}
	
	
	// **** MÉTODOS DE DELETE ****
	
	/**
	 * Deleta categoria no banco de dados
	 * @param categoria Objeto Categoria
	 */
	public void deleteCategoria(Categoria categoria) {
		log.info("[CategoriaService] : in deleteCategoria");
		
		// Verifica se existe categoria no banco de dados
		Categoria delCategoria = findById(categoria.getId_categoria());
		
		// Deleta subcategorias
		List<Subcategoria> delSubcategorias = subcategoriaRepository.findByCategoria(delCategoria);
		for (Subcategoria delSubcategoria : delSubcategorias) {
			
			// Deletar a subcategoria registrada nos produtos
			List<Product> produtos = productRepository.findBySubcategoria(delSubcategoria);
			for(Product produto : produtos) {
				produto.setSubcategoria(null);
			}
			
			subcategoriaRepository.delete(delSubcategoria);
		}
		
		
		// Deleta Categoria
		categoriaRepository.delete(delCategoria);
		
		log.info("[CategoriaService] : end deleteCategoria");
	}	
}
