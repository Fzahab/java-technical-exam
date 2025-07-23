package com.monty.exam.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monty.exam.configuration.UrlConstants;
import com.monty.exam.dto.model.UserDto;
import com.monty.exam.facade.UserFacade;

@RestController
@RequestMapping(value=UrlConstants.API_ROOT_PATH, consumes = {MediaType.APPLICATION_JSON_VALUE} )
public class UserControler {

	@Autowired
	private UserFacade userFacade;
		
	@GetMapping(UrlConstants.USER)
	public UserDto registerUser(Authentication authentication) {
		
		String email = ((UserDetails) authentication.getPrincipal()).getUsername();	
		
		return userFacade.getUserByEmail(email);
	}
	
}
