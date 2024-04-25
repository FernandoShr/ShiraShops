package com.shirashop.productapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO da entidade Product sem Categoria e Subcategoria
 * @author Fernando Shiraishi
 *
 */
@JsonPropertyOrder({"idProduto","nomeProduto","descricao",
					"sku","data_criacao","valor_unitario",
					"estoque"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	// Atributos
	
	private Integer idProduto;
	@JsonProperty("nome_produto")
	private String nomeProduto;
	private String descricao;
	private Integer sku;
	private String data_criacao;
	private Double valor_unitario;
	private Integer estoque;
	
	
	
	
}
