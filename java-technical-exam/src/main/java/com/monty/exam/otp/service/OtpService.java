package com.monty.exam.otp.service;

public interface OtpService {

	public String generateAndStoreOtp(String email);

	public boolean validateOtp(String email, String inputOtp);

}
