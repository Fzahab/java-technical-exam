package com.monty.exam.otp.message.service;

public interface MessagePublisher {
    public void sendOtp(String email, String otp) ;
}
