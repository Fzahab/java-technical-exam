package com.monty.exam.controler;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monty.exam.configuration.UrlConstants;
import com.monty.exam.core.model.OneTimePassword;

@RestController
@RequestMapping(value=UrlConstants.API_ROOT_PATH, consumes = {MediaType.APPLICATION_JSON_VALUE} )
public class AuthControler {

	
	
	@PostMapping(UrlConstants.REGISTER)
	public OneTimePassword registerUser() {
		return null;
	}
	
	@PostMapping(UrlConstants.login)
	public OneTimePassword login() {
	    return null;
	}

	
	@PostMapping(UrlConstants.VERIFY)
	public OneTimePassword verifyOtp() {
		return null;
	}
	
	
	
}
