package com.shirashop.pedidoapi.entities.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subcategoria {
	
	private Integer id_sub_categoria;
	private String nomeSubcategoria;
	private String descricao;
	private Categoria categoria;
	
	
}
