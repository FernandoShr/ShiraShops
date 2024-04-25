package com.shirashop.authentication.controller;

import com.shirashop.authentication.request.AuthRequest;
import com.shirashop.authentication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shirashop.authentication.entities.User;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService service;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public String addNewUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest authRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authenticate.isAuthenticated()) {
			return service.generateToken(authRequest.getEmail());
		} else {
			throw new RuntimeException("invalid access");
		}
	}
}
