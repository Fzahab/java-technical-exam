package com.monty.exam.facade.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monty.exam.core.model.User;
import com.monty.exam.dto.model.DtoMapper;
import com.monty.exam.dto.model.LoginRequest;
import com.monty.exam.dto.model.OtpVerificationRequest;
import com.monty.exam.dto.model.RegisterRequest;
import com.monty.exam.dto.model.TokenRespond;
import com.monty.exam.dto.model.UserDto;
import com.monty.exam.facade.AuthFacade;
import com.monty.exam.user.service.AuthService;

@Component
public class AuthFacadeImpl implements AuthFacade {

	@Autowired
	AuthService authService;
	
	@Override
	public UserDto registerNewUser(RegisterRequest registerRequest) throws Exception {
		User user = DtoMapper.fromRegisterRequestToUser(registerRequest);
		User registeredUser = authService.register(user);
		return DtoMapper.userToUserDto(registeredUser);
	}

	@Override
	public TokenRespond logIn(LoginRequest loginRequest) {
		String token = authService.login(loginRequest.getEmail(),loginRequest.getPassword());
		return new TokenRespond(loginRequest.getEmail(),token,LocalDateTime.now());
	}

	@Override
	public Boolean verifyOtp(OtpVerificationRequest request) {

		return authService.verifyOtp(request.getEmail(), request.getOtp());
	}

}
