package com.shirashop.pedidoapi.entities;

import java.math.BigDecimal;

import com.shirashop.pedidoapi.dto.ProductDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalhePedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalhePedido;
	
	@Column
	private Integer quantidade;
	
	//Calculado automaticamente
	@Column
	private BigDecimal subTotal;

	@Embedded
	private ProductDTO produto;
	

}
