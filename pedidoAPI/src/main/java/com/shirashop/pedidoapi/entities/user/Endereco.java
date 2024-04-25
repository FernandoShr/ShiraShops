package com.shirashop.pedidoapi.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

	private Integer id_endereco;
	private String rua;
	private String numero;
	private String cep;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private Pais pais;
	
}
