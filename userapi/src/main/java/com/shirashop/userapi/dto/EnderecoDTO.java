package com.shirashop.userapi.dto;

import com.shirashop.userapi.entities.Endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
	
	private Integer idUsuario;
	private Endereco endereco;
	
}
