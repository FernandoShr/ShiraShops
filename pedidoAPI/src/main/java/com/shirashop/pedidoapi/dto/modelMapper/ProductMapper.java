package com.shirashop.pedidoapi.dto.modelMapper;

import java.util.ArrayList;
import java.util.List;

import com.shirashop.pedidoapi.dto.ProductDTO;
import com.shirashop.pedidoapi.dto.ProductOrder;
import com.shirashop.pedidoapi.entities.product.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductMapper {

	private final ModelMapper mapper;
	
	public ProductOrder toProductOrder(ProductDTO product) {
		return mapper.map(product, ProductOrder.class);
	}
	
	public ProductDTO toProductDTO(Product product) {
		return mapper.map(product, ProductDTO.class);
	}

	public Product toProduct(ProductDTO product) {
		return mapper.map(product, Product.class);
	}
	
	public List<Product> toListProduct(List<ProductDTO> products){
		List<Product> prodList = new ArrayList<>();
		for(ProductDTO prod: products) {
			prodList.add(toProduct(prod));
		}
		
		return prodList;
	}	
	
}
