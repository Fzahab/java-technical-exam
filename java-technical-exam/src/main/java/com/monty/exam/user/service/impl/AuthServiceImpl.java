package com.monty.exam.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.monty.exam.core.model.User;
import com.monty.exam.otp.message.service.MessagePublisher;
import com.monty.exam.otp.service.OtpService;
import com.monty.exam.security.JwtService;
import com.monty.exam.user.service.AuthService;
import com.monty.exam.user.service.UserService;

public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserService userService;
	@Autowired
	private OtpService otpService;
//	@Autowired
//	private MessagePublisher messagePublisher;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
    
	@Override
	public User register(User user) {
		
		User savedUser = userService.save(user);

		String otp = otpService.generateAndStoreOtp(savedUser.getEmail());
	//	messagePublisher.sendOtp(savedUser.getEmail(), otp);

		return savedUser;
	}

	@Override
	public boolean verifyOtp(String email, String otp) {
	
	User user = userService.findByEmail(email);
		boolean isValid = otpService.validateOtp(email, otp);
		if (isValid) {
			user.setActive(true);
			userService.update(user);
			return true;
		}
		return false;
	}

	@Override
	public String login(String email, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, email));

		User user = userService.findByEmail(email);
				
		String jwt = jwtService.generateToken(user);

		return jwt;
	}

}
