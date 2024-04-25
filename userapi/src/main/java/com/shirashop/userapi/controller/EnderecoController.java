package com.shirashop.userapi.controller;

import java.util.List;

import com.shirashop.userapi.dto.EnderecoDTO;
import com.shirashop.userapi.entities.Endereco;
import com.shirashop.userapi.service.EnderecoService;
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

@RestController
@RequestMapping("/user/endereco") 
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	// **** MÉTODOS DE CONSULTA ****
	
	@GetMapping(path="/id/{id_endereco}")
	public ResponseEntity<Endereco> findEnderecoById(@PathVariable("id_endereco") Integer enderecoId){
		
		Endereco endereco = enderecoService.findById(enderecoId);
		
		return new ResponseEntity<Endereco>(endereco,HttpStatus.OK);
	}	

	@GetMapping(path="/userId/{userId}")
	public ResponseEntity<List<Endereco>> findEnderecoByUserId(@PathVariable("userId") Integer userId){
		
		List<Endereco> endereco = enderecoService.findByUserId(userId);
		
		return new ResponseEntity<List<Endereco>>(endereco,HttpStatus.OK);
	}	

	
	// **** MÉTODOS DE CREATE ****
	@PostMapping
	public ResponseEntity<String> saveEndereco(@RequestBody EnderecoDTO endereco){
		
		enderecoService.saveEndereco(endereco.getEndereco(), endereco.getIdUsuario());
		
		return new ResponseEntity<String>("Endereço registrado com sucesso!", HttpStatus.CREATED);
	}
	
	// **** MÉTODOS DE UPDATE ****
	@PutMapping
	public ResponseEntity<String> updateEndereco(){
		
		return new ResponseEntity<String>("Endereço atualizado com sucesso!", HttpStatus.OK);
	}
	
	// **** MÉTODOS DE DELEÇÃO ****
	@DeleteMapping
	public ResponseEntity<String> deleteEndereco(){
		
		return new ResponseEntity<String>("Endereço deletado com sucesso!", HttpStatus.OK);
	}
	
}
