package com.monty.exam.otp.message.service;

import java.util.Map;

public interface MessageConsumer {
	
	public void receiveOtp(Map<String, String> message);

}
