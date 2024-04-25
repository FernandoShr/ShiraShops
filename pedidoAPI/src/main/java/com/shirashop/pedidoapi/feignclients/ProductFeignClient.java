package com.shirashop.pedidoapi.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.shirashop.pedidoapi.dto.ProductOrder;
import com.shirashop.pedidoapi.entities.product.Product;

@Component
@FeignClient(name = "productapi",  path="/product")//url = "localhost:8888"
public interface ProductFeignClient {

	@GetMapping(path = "/id/{id_produto}")
	public ResponseEntity<Product> consultingId(@PathVariable("id_produto") int id_produto);
	
	@PutMapping(path="/subStock")
	public Product subtraiEstoque(@RequestBody Product product,@RequestParam Integer quantidade); 
	
	@PutMapping(path="/returnStock")
	public void retornaEstoque(@RequestBody List<ProductOrder> product);
	
}
