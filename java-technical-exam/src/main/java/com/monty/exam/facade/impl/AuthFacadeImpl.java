package com.monty.exam.facade.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monty.exam.core.model.User;
import com.monty.exam.dto.mapper.DtoMapper;
import com.monty.exam.dto.model.LoginRequest;
import com.monty.exam.dto.model.OtpVerificationRequest;
import com.monty.exam.dto.model.RegisterRequest;
import com.monty.exam.dto.model.TokenRespond;
import com.monty.exam.dto.model.UserDto;
import com.monty.exam.facade.AuthFacade;
import com.monty.exam.user.service.AuthService;

@Component
public class AuthFacadeImpl implements AuthFacade {
	private static final Logger log = LoggerFactory.getLogger(AuthFacadeImpl.class);

	@Autowired
	AuthService authService;
	@Override
	public UserDto registerNewUser(RegisterRequest registerRequest) throws Exception {
		log.info("Register user with email {}",registerRequest.getEmail());
		User user = DtoMapper.fromRegisterRequestToUser(registerRequest);
		User registeredUser = authService.register(user);
		return DtoMapper.userToUserDto(registeredUser);
	}

	@Override
	public TokenRespond logIn(LoginRequest loginRequest) {
		log.info("Login in for user with email {}",loginRequest.getEmail());
		String token = authService.login(loginRequest.getEmail(),loginRequest.getPassword());
		return new TokenRespond(loginRequest.getEmail(),token,LocalDateTime.now());
	}

	@Override
	public Boolean verifyOtp(OtpVerificationRequest request) {
		log.info("verifying Otp Code of user with email {}",request.getEmail());

		return authService.verifyOtp(request.getEmail(), request.getOtp());
	}

}
