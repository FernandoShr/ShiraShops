package com.shirashop.authentication.service;

import java.util.Optional;

import com.shirashop.authentication.entities.User;
import com.shirashop.authentication.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shirashop.authentication.repository.UserRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Optional<User> credential =  Optional.of(repository.findByEmail(username));
		return credential.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));

	}

}
