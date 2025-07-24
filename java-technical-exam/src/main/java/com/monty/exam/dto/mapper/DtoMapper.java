package com.monty.exam.dto.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monty.exam.core.model.User;
import com.monty.exam.dto.model.RegisterRequest;
import com.monty.exam.dto.model.UserDto;

public class DtoMapper {
	private static final Logger log = LoggerFactory.getLogger(DtoMapper.class);

	public static User fromRegisterRequestToUser(RegisterRequest registerRequest) {
		log.info("Map Data from Register Request to User");
		User user = new User();
		user.setName(registerRequest.getName());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(registerRequest.getPassword());
		user.setAge(registerRequest.getAge());
		return user;
	}

	public static UserDto userToUserDto(User user) {
		log.info("Map Data from User to User Dto");
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAge(user.getAge());
		userDto.setActive(user.getActive());
		userDto.setCreatedAt(user.getCreatedAt());
		return userDto;
	}

	
	
	
}
