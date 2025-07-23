package com.monty.exam.dto.model;

import com.monty.exam.core.model.User;

public class DtoMapper {

	public static User fromRegisterRequestToUser(RegisterRequest registerRequest) {
		User user = new User();
		user.setName(registerRequest.getName());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(registerRequest.getPassword());
		user.setAge(registerRequest.getAge());
		return user;
	}

	public static UserDto userToUserDto(User user) {
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
