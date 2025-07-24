package com.monty.exam.otp.service.impl;

import java.time.Duration;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.monty.exam.otp.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService {
	
	private static final Logger log = LoggerFactory.getLogger(OtpServiceImpl.class);

	@Autowired
	private  RedisTemplate<String, String> redisTemplate;
    private final Random random = new Random();
	
	public OtpServiceImpl(RedisTemplate<String, String> redisTemplate) {
			super();
			this.redisTemplate = redisTemplate;
		}

	@Override
	public String generateAndStoreOtp(String email) {
		log.info("generating Otp code for {} and saving it in memory ",email);
		
		String otp = String.format("%06d", random.nextInt(999999));
        String key = "otp:" + email;

        redisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(5));
        return otp;
   }

	@Override
	public boolean validateOtp(String email, String inputOtp) {
		log.info("validating Otp code for {} ",email);
		
        String key = "otp:" + email;
        String storedOtp = redisTemplate.opsForValue().get(key);
        return inputOtp.equals(storedOtp);
   }

}
