package com.monty.exam.otp.service.impl;

import java.time.Duration;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.monty.exam.otp.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService {

	@Autowired
	private  RedisTemplate<String, String> redisTemplate;
    private final Random random = new Random();
	
	public OtpServiceImpl(RedisTemplate<String, String> redisTemplate) {
			super();
			this.redisTemplate = redisTemplate;
		}

	@Override
	public String generateAndStoreOtp(String email) {
        
		String otp = String.format("%06d", random.nextInt(999999));
        String key = "otp:" + email;

        redisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(5));
        return otp;
   }

	@Override
	public boolean validateOtp(String email, String inputOtp) {
        String key = "otp:" + email;
        String storedOtp = redisTemplate.opsForValue().get(key);
        return inputOtp.equals(storedOtp);
   }

}
