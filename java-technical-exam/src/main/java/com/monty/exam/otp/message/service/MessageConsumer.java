package com.monty.exam.otp.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface MessageConsumer {
	
	public void receiveOtp(String oneTimePassword) throws JsonMappingException, JsonProcessingException;

}
