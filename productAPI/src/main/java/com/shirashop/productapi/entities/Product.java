package com.shirashop.productapi.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonPropertyOrder({"idProduto","nomeProduto","descricao",
					"sku","data_criacao","valor_unitario",
					"estoque"})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduto;
	
	// Atributos padrões
	@Column(nullable= false, length = 45, name="nome_produto")
	private String nomeProduto;
	@Column(nullable = false, length = 255)
	private String descricao;
	@Column(nullable = false)
	private Integer sku;
	@Column
	private String data_criacao;
	@Column(nullable=false)
	private Double valor_unitario;
	@Column(nullable=false)
	private Integer estoque;
	
	// Relacionamentos
	@ManyToOne
	private Subcategoria subcategoria;
	
}
