package com.monty.exam.otp.message.service;

import com.monty.exam.core.model.OneTimePassword;

public interface MessagePublisher {

	public void sendOtp(OneTimePassword oneTimePassword) throws Exception;
}
