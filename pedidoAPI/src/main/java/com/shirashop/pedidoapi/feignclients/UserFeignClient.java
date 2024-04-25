package com.shirashop.pedidoapi.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shirashop.pedidoapi.entities.user.UserModel;



@Component
@FeignClient(name="userApi",  path = "/user")//url = "localhost:8001"
public interface UserFeignClient {

	// Consulta único usuário pelo id
	@GetMapping(path = "/id/{id_usuario}")
	public UserModel consultingId(@PathVariable("id_usuario") Integer id_usuario);
	
}
