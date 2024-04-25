package com.shirashop.productapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
public class ProductapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductapiApplication.class, args);
	}

}
