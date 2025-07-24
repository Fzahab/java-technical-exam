package com.monty.exam.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monty.exam.constants.UrlConstants;
import com.monty.exam.dto.model.UserDto;
import com.monty.exam.facade.UserFacade;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value=UrlConstants.API_ROOT_PATH, consumes = {MediaType.APPLICATION_JSON_VALUE} )
public class UserControler {
    private static final Logger log = LoggerFactory.getLogger(UserControler.class);

	@Autowired
	private UserFacade userFacade;
		
	@GetMapping(UrlConstants.USER)
    @Operation(summary = "Get user Data", description = "getting user Data")
	public UserDto registerUser(Authentication authentication) {
		log.info("getting user Data ");
		String email = ((UserDetails) authentication.getPrincipal()).getUsername();	
		
		return userFacade.getUserByEmail(email);
	}
	
}
