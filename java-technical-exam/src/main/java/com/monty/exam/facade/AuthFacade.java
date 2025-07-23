package com.monty.exam.facade;

import com.monty.exam.dto.model.LoginRequest;
import com.monty.exam.dto.model.OtpVerificationRequest;
import com.monty.exam.dto.model.RegisterRequest;
import com.monty.exam.dto.model.TokenRespond;
import com.monty.exam.dto.model.UserDto;

public interface AuthFacade {

	UserDto registerNewUser(RegisterRequest registerRequest) throws Exception;

	TokenRespond logIn(LoginRequest loginRequest);

	Boolean verifyOtp(OtpVerificationRequest request);

}
