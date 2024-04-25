package com.shirashop.productapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.shirashop.productapi.constants.ProductConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shirashop.productapi.entities.Product;
import com.shirashop.productapi.repository.CategoriaRepository;
import com.shirashop.productapi.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = ProductService.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@Test
	public void getProduct_ByExistingId_ReturnsProduct() {
		// AAA - ARRANGE ACT ASSERT
		
		// ARRANGE
		when(productRepository.findById(ProductConstants.VALID_PRODUCT.getIdProduto())).thenReturn(Optional.of(ProductConstants.VALID_PRODUCT));
		
		// ACT
		// system under test
		Product sut = productService.findById(ProductConstants.VALID_PRODUCT.getIdProduto());
		
		// ASSERT
		assertThat(sut).isNotNull();
		assertThat(sut).isEqualTo(ProductConstants.VALID_PRODUCT);
	}
	
	@Test
	public void getProduct_ByNullId_ThrowEntityNotFoundException() {
		
		
		
		Product sut = productService.findById(null);
		
	}

	@Test
	public void getProduct_ByExistingName_ReturnsListProduct() {
		// Lista esperada
		List<Product> products = new ArrayList<>();
		products.add(ProductConstants.VALID_PRODUCT);
		when(productRepository.findByNomeProdutoContaining(ProductConstants.VALID_PRODUCT.getNomeProduto())).thenReturn(products);
		
		// Serviço teste
		List<Product> sut = productService.findByNome(ProductConstants.VALID_PRODUCT.getNomeProduto());
		
		assertThat(sut).isEqualTo(products);
	}
	
//	@Test
//	public void createProduct_WithValidData_ReturnsProduct() {
//		// Testes AAA
//		// Arrange (seleção dos dados)
//		when(productRepository.save(PRODUCT)).thenReturn(PRODUCT);
//		
//		// Act (ação/lógica)
//		Product sut = productService.saveProduct(PRODUCT);
//		
//		// Assert (aferir se o sistema sobre teste(sut) é o que esperávamos)
//		assertThat(sut).isEqualTo(PRODUCT);
//	}
//	
//	@Test
//	public void saveProduct_WithInvalidData_ThrowException() {
//		
//		productService.saveProduct(INVALID_PRODUCT);
//	}
	
}
