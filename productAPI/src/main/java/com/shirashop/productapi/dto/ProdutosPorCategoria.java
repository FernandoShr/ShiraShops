package com.shirashop.productapi.dto;

import java.util.List;

import com.shirashop.productapi.entities.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe DTO que reúne Categoria com seus respectivos Produtos
 * @author Fernando Shiraishi
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosPorCategoria {
	
	Categoria categoria;
	
	List<ProductDTO> produtos;

}
