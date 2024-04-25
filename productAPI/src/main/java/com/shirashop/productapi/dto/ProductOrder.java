package com.shirashop.productapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
	
	private Integer idProduto;
	private String nomeProduto;
	private Double valor_unitario;
	private Integer quantidade;
	

}
