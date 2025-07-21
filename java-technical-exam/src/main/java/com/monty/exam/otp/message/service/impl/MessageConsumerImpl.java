package com.monty.exam.otp.message.service.impl;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.monty.exam.otp.message.service.MessageConsumer;
import com.monty.exam.otp.message.service.OtpMessageConstants;

public class MessageConsumerImpl implements MessageConsumer {

	@RabbitListener(queues = "otpQueue")
	public void receiveOtp(Map<String, String> message) {
		String email = message.get(OtpMessageConstants.EMAIL_PAYLOAD_KEY);
		String otp = message.get(OtpMessageConstants.OTP_PAYLOAD_KEY);

		System.out.println("receive Otp for Email " );
	}
}
