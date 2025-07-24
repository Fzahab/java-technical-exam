package com.monty.exam.otp.message.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monty.exam.configuration.ApplicationProperties;
import com.monty.exam.core.model.OneTimePassword;
import com.monty.exam.otp.message.service.MessagePublisher;
@Component
public class MessagePublisherImpl implements MessagePublisher {
	
	private static final Logger log = LoggerFactory.getLogger(MessagePublisherImpl.class);

	@Autowired
	private  RabbitTemplate rabbitTemplate;
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
    private ApplicationProperties appProperties;
	public MessagePublisherImpl(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void sendOtp(OneTimePassword oneTimePassword)throws Exception  {
		log.info("Sending Otp Message to the queue {}","otpQueue");
		
		String writeValueAsString = mapper.writeValueAsString(oneTimePassword);
		
		rabbitTemplate.convertAndSend(appProperties.otpQueueName, writeValueAsString);
	}

}
