package com.shirashop.userapi.controller;

import java.util.List;

import com.shirashop.userapi.entities.UserModel;
import com.shirashop.userapi.exceptions.EntityNotFoundException;
import com.shirashop.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(path = "/user")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
		
	//********* MÉTODOS DE CONSULTA *************

	// Consulta todos os usuários cadastrados
	@GetMapping(path = "/all")
	public List<UserModel> consultingAll() {
		List<UserModel> users = (List<UserModel>) userService.findAll();
		
		if(users.size() == 0) {
			throw new EntityNotFoundException("Não há usuários cadastrados!");
		}
		
		return users;
	}
	
	// Consulta único usuário pelo id
	@GetMapping(path = "/id/{id_usuario}")
	public UserModel consultingId(@PathVariable("id_usuario") Integer id_usuario) {
		UserModel user = userService.findByIdAndActive(id_usuario);
		return user;
	}
	
	// Consulta pelo nome
	@GetMapping(path="/nome/{nome}")
	public ResponseEntity<List<UserModel>> consultingByName(@PathVariable("nome") String nome) {
		return new ResponseEntity<List<UserModel>>(userService.findByNome(nome), HttpStatus.OK);
	}
	
	
	// Consulta pelo cpf
	@GetMapping(path = "/cpf/{cpf}")
	public ResponseEntity<UserModel> consultingByCPF (@PathVariable("cpf") String cpf) {
		return new ResponseEntity<UserModel>(userService.findByCpf(cpf), HttpStatus.OK);
	}
	
	//******** MÉTODOS DE CREATE *************
	
	// Cria e salva Usuário
	@PostMapping
	public ResponseEntity<String> save(@RequestBody UserModel user) {
		// Valida e Salva o usuário pela camada de serviço 
		userService.saveUser(user);
		return new ResponseEntity<String>("Usuário criado com sucesso!", HttpStatus.CREATED);
	}
	
	
	
	//******** MÉTODOS DE UPDATE *************
	
	// Atualiza Usuário
	@PutMapping
	public String update (@RequestBody UserModel user) {
		
			userService.updateUser(user);
			return "Usuário atualizado com sucesso";

	}
	
	// Reativa Usuário
	@PutMapping(path="/reativar")
	public String reativar (@RequestBody UserModel user) {
		// Se o usuário for encontrado no banco de dados, reative
		if (userService.findById(user.getIdUsuario()) != null) {
			userService.reactiveUser(user);			
			return "Usuário reativado com sucesso!";
		}			
	return "Usuário não encontrado na nossa base de dados";	
	}
	
	//******** MÉTODOS DE DELETE *************
	
	// Deleta Usuário
	@DeleteMapping
	public String excluir (@RequestBody UserModel user) {
		// Se o usuário for encontrado no banco de dados, delete
			if (consultingId(user.getIdUsuario()) != null) {
				userService.deleteUser(user);			
				return "Usuário deletado com sucesso!";
			}			
		return "Usuário não encontrado na nossa base de dados";
	}
		
	
}


