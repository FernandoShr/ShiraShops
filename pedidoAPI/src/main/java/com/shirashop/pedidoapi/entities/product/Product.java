package com.shirashop.pedidoapi.entities.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private Integer idProduto;
	private String nomeProduto;
	private String descricao;
	private Integer sku;
	private String data_criacao;
	private Double valor_unitario;
	private Integer estoque;
	private Subcategoria subcategoria;
	
}
