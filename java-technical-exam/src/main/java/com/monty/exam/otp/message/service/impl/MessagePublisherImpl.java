package com.monty.exam.otp.message.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.monty.exam.otp.message.service.MessagePublisher;
import com.monty.exam.otp.message.service.OtpMessageConstants;

public class MessagePublisherImpl implements MessagePublisher {

	private  RabbitTemplate rabbitTemplate;

	public MessagePublisherImpl(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void sendOtp(String email, String otp) {
		Map<String, String> payload = new HashMap<String, String>();
		payload.put(OtpMessageConstants.EMAIL_PAYLOAD_KEY, email);
		payload.put(OtpMessageConstants.OTP_PAYLOAD_KEY, otp);

		rabbitTemplate.convertAndSend("otpQueue", payload);
	}

}
