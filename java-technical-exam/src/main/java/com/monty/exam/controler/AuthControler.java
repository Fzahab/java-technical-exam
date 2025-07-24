package com.monty.exam.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monty.exam.constants.UrlConstants;
import com.monty.exam.dto.model.LoginRequest;
import com.monty.exam.dto.model.OtpVerificationRequest;
import com.monty.exam.dto.model.RegisterRequest;
import com.monty.exam.dto.model.TokenRespond;
import com.monty.exam.dto.model.UserDto;
import com.monty.exam.facade.AuthFacade;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value=UrlConstants.AUTH_ROOT_PATH, consumes = {MediaType.APPLICATION_JSON_VALUE} )

public class AuthControler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	AuthFacade authFacade;
	
	@PostMapping(UrlConstants.REGISTER)
    @Operation(summary = "Register new user", description = "Creates a user and sends OTP")
	public UserDto registerUser(@RequestBody RegisterRequest registerRequest ) throws Exception {
		log.info("Register user with email {}",registerRequest.getEmail());
		return authFacade.registerNewUser(registerRequest);
	}
	
	@PostMapping(UrlConstants.login)
    @Operation(summary = "log in User", description = "Log in a user and receive a token")
	public TokenRespond login(@RequestBody LoginRequest loginRequest ) {
		log.info("Login in for user with email {}",loginRequest.getEmail());

	    return authFacade.logIn(loginRequest);
	}

	
	@PostMapping(UrlConstants.VERIFY)
    @Operation(summary = "Verify otp and activation Of a user ", description = "return If user got activated or no")
	public Boolean verifyOtp(@RequestBody OtpVerificationRequest request  ) {
		log.info("verifying Otp Code of user with email {}",request.getEmail());
		
		return authFacade.verifyOtp(request);
	}
	
	
	
}
