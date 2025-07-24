package com.monty.exam.security;

import org.springframework.security.core.userdetails.UserDetails;

import com.monty.exam.core.model.User;

public interface JwtService {
	public String generateToken(User user) ;

	public String extractUsername(String token) ;

	public boolean isTokenValid(String token, UserDetails userDetails) ;
}
