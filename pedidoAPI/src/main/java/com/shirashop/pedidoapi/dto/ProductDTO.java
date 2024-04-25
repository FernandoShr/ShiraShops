package com.shirashop.pedidoapi.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductDTO {
	
	private Integer idProduto;
	private String nomeProduto;
	private Double valor_unitario;
	
}
