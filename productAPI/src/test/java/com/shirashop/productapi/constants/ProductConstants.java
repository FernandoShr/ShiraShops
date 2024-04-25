package com.shirashop.productapi.constants;

import com.shirashop.productapi.entities.Categoria;
import com.shirashop.productapi.entities.Product;
import com.shirashop.productapi.entities.Subcategoria;

public class ProductConstants {
	
	public static final Product VALID_PRODUCT = 
			new Product(1, "nome", "descrição com mais de 15 caracteres", 1234,
				"06/04/2023", 50.2, 20,
				new Subcategoria(1, "subcateg", "descricaotestestestes", 
					new Categoria(1, "nomeCateg")));
	
	
	public static final Product INVALID_PRODUCT = 
			new Product(0, "", "", 0,
				"", 0.0, 0,
				new Subcategoria(0, "", "", 
					new Categoria(0, "")));
}
