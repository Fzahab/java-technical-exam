package com.monty.exam.user.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.monty.exam.core.model.OneTimePassword;
import com.monty.exam.core.model.User;
import com.monty.exam.otp.message.service.MessagePublisher;
import com.monty.exam.otp.service.OtpService;
import com.monty.exam.security.JwtService;
import com.monty.exam.user.service.AuthService;
import com.monty.exam.user.service.UserService;

@Component
public class AuthServiceImpl implements AuthService {
	
	private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private OtpService otpService;
	@Autowired
	private MessagePublisher messagePublisher;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
    
	@Override
	public User register(User user) throws Exception {
		log.info("Register user with email {}",user.getEmail());

		User savedUser = userService.save(user);

		String otp = otpService.generateAndStoreOtp(savedUser.getEmail());
		
		OneTimePassword oneTimePassword =createOneTimePassword(otp, savedUser);
	
		messagePublisher.sendOtp(oneTimePassword);

		return savedUser;
	}

	@Override
	public boolean verifyOtp(String email, String otp) {
		log.info("verifying Otp Code of user with email {}",email);

	User user = userService.findByEmail(email);
		boolean isValid = otpService.validateOtp(email, otp);
		if (isValid) {
			log.info("Otp Code verified");
			user.setActive(true);
			userService.update(user);
			return true;
		}
		return false;
	}

	@Override
	public String login(String email, String password) {
		log.info("Login in for user with email {}",email);

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		User user = userService.findByEmail(email);
				
		String jwt = jwtService.generateToken(user);

		return jwt;
	}
	
	
	private OneTimePassword createOneTimePassword(String otp,User savedUser) {

	OneTimePassword oneTimePassword = new OneTimePassword();
	oneTimePassword.setCode(otp);
	oneTimePassword.setExpirationTime(LocalDateTime.now().plusMinutes(5));
	oneTimePassword.setUsrId(savedUser.getId());
	oneTimePassword.setVerified(false);
	return oneTimePassword;
	
	}
}
