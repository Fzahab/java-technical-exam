package com.monty.exam.otp.message.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monty.exam.core.model.OneTimePassword;
import com.monty.exam.otp.message.service.MessagePublisher;
@Component
public class MessagePublisherImpl implements MessagePublisher {

	@Autowired
	private  RabbitTemplate rabbitTemplate;
	@Autowired
	private ObjectMapper mapper;
	
	public MessagePublisherImpl(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void sendOtp(OneTimePassword oneTimePassword)throws Exception  {
		
		String writeValueAsString = mapper.writeValueAsString(oneTimePassword);
		
		rabbitTemplate.convertAndSend("otpQueue", writeValueAsString);
	}

}
