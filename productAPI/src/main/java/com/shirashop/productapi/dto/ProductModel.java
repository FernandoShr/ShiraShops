package com.shirashop.productapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.shirashop.productapi.entities.Categoria;
import com.shirashop.productapi.entities.Subcategoria;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe DTO modelo para os métodos Get
 * @author Fernando Shiraishi
 *
 */
@JsonPropertyOrder({"id_produto","nomeProduto","descricao",
					"sku","data_criacao","valor_unitario",
					"estoque"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

	private Integer id_produto;
	@JsonProperty("nome_produto")
	private String nomeProduto;
	private String descricao;
	private Integer sku;
	@Column(nullable = true)
	private String data_criacao;
	private Double valor_unitario;
	private Integer estoque;
	private Subcategoria subcategoria;
	private Categoria categoria;
}
