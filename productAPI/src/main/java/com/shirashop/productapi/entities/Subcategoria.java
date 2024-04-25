package com.shirashop.productapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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

@JsonPropertyOrder({"id_sub_categoria","nomeSubcategoria",
					"descricao","categoria"})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subcategoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_sub_categoria;
	
	@Column(nullable= false, length = 45, name="nome_sub_categoria")
	@JsonProperty("nome_sub_categoria")
	private String nomeSubcategoria;
	
	@Column(nullable = false)
	private String descricao;
	
	//Relacionametos
	@ManyToOne
	private Categoria categoria;
	
	
}
