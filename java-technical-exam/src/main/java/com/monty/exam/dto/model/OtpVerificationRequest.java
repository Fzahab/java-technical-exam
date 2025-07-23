package com.monty.exam.dto.model;

public class OtpVerificationRequest {

	String email;
	String otp;

	public OtpVerificationRequest() {
		super();
	}
	public OtpVerificationRequest(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}


}
