package com.shirashop.authentication.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shirashop.authentication.entities.User;
import com.shirashop.authentication.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public String saveUser(User credential) {
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		repository.save(credential);
		return "user added to the system";

	}

	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}
}