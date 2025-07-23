package com.monty.exam.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monty.exam.configuration.UrlConstants;
import com.monty.exam.dto.model.LoginRequest;
import com.monty.exam.dto.model.OtpVerificationRequest;
import com.monty.exam.dto.model.RegisterRequest;
import com.monty.exam.dto.model.TokenRespond;
import com.monty.exam.dto.model.UserDto;
import com.monty.exam.facade.AuthFacade;

@RestController
@RequestMapping(value=UrlConstants.AUTH_ROOT_PATH, consumes = {MediaType.APPLICATION_JSON_VALUE} )
public class AuthControler {

	@Autowired
	AuthFacade authFacade;
	
	@PostMapping(UrlConstants.REGISTER)
	public UserDto registerUser(@RequestBody RegisterRequest registerRequest ) throws Exception {
		return authFacade.registerNewUser(registerRequest);
	}
	
	@PostMapping(UrlConstants.login)
	public TokenRespond login(@RequestBody LoginRequest loginRequest ) {
	    return authFacade.logIn(loginRequest);
	}

	
	@PostMapping(UrlConstants.VERIFY)
	public Boolean verifyOtp(@RequestBody OtpVerificationRequest request  ) {
		return authFacade.verifyOtp(request);
	}
	
	
	
}
