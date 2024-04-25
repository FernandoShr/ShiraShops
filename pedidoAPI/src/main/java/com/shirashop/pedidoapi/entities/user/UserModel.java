package com.shirashop.pedidoapi.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	private Integer id_usuario;
	private String nome;
	private String sobrenome;
	private String cpf;
	private String data_criacao;
	private String data_modificacao;
	private String telefone;
	private boolean ativo;
	private Endereco endereco;	
	
}
