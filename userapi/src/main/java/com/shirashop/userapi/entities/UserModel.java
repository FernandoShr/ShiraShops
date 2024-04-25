package com.shirashop.userapi.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	//   ATRIBUTOS PADRÕES    
	@Column(length=45, nullable=false)
	private String nome;
	
	@Column(length=250, nullable = false)
	private String sobrenome;
	
	@Column(length=45, nullable = false)
	private String cpf;
	
	@Column 
	private String data_criacao;
	
	@Column(nullable = true)
	private String data_modificacao;
	
	@Column(nullable = true)
	private String telefone;
	
	@Column
	private boolean ativo;
	
	@Column
	private boolean admin;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	
	//  RELACIONAMENTOS
	@OneToMany
	@JoinColumn(name="userId")
	private List<Endereco> endereco;
	
	
}
