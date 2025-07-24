package com.monty.exam.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monty.exam.core.model.User;
import com.monty.exam.dto.mapper.DtoMapper;
import com.monty.exam.dto.model.UserDto;
import com.monty.exam.facade.UserFacade;
import com.monty.exam.user.service.UserService;

@Component
public class UserFacadeImpl implements UserFacade {

	@Autowired
	UserService userService;

	
	@Override
	public UserDto getUserByEmail(String email) {
		User findByEmail = userService.findByEmail(email);
		return DtoMapper.userToUserDto(findByEmail);
	}
	

}
