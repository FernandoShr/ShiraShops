package com.shirashop.productapi.dto;

import java.util.List;

import com.shirashop.productapi.entities.Subcategoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosPorSubcategoria {

	Subcategoria subcategoria;
	
	List<ProductDTO> produtos;
	
}
