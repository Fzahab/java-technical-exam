package com.monty.exam.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
	
	 @Value("${jwt.token.secret}")
	 public String secretKey;
	 @Value("${otp.message.queue}")
	 public String otpQueueName;
	 
}
