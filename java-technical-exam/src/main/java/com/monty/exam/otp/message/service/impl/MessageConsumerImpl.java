package com.monty.exam.otp.message.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monty.exam.core.model.OneTimePassword;
import com.monty.exam.otp.message.service.MessageConsumer;

@Component
public class MessageConsumerImpl implements MessageConsumer {
	@Autowired
	ObjectMapper objectMapper;

	@RabbitListener(queues ="${otp.message.queue}")
	public void receiveOtp(String oneTimePassword) throws JsonMappingException, JsonProcessingException {
		OneTimePassword otpObj = objectMapper.readValue(oneTimePassword, OneTimePassword.class);
		
		System.out.println("receive Otp for user id  "+otpObj.getUsrId()+" :"+otpObj.getCode() );
	}
}
