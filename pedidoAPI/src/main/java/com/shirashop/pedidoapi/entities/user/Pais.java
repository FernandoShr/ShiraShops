package com.shirashop.pedidoapi.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pais {

	private Integer id_country;
	private String nome;
	private String codigo;
	
}
