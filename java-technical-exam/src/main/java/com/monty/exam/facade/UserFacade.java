package com.monty.exam.facade;

import com.monty.exam.dto.model.UserDto;

public interface UserFacade {

	UserDto getUserByEmail(String email);
	

}
