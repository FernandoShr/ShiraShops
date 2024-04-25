package com.shirashop.apigateway.auth;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	
	public static final List<String> openApiEndpoints = List.of(
			"/auth/register",
			"/auth/token",
			"/eureka",
			"/product/all",
			"/product/nome",
			"/product/id",
			"/product/subcategoria/all",
			"/product/categoria/all"
			);

	public static List<String> adminApiEndpoints = List.of(
			"/user",
			"/product",
			"/pedido",
			"/auth",
			"/eureka");
	
	public Predicate<ServerHttpRequest> isSecured =
			request -> openApiEndpoints
			.stream()
			.noneMatch(uri ->
			request.getURI().getPath().contains(uri));

}
