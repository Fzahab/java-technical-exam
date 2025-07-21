package com.monty.exam.user.service;

import com.monty.exam.core.model.User;

public interface AuthService {
	 public User register(User user); 
	 
	 public boolean verifyOtp(String email, String otp); 
	 //jwtToken
	 public String login(String email, String password); 
	 
}
