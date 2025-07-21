package com.monty.exam.user.service.impl;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.monty.exam.core.model.User;
import com.monty.exam.db.model.UserDbModel;
import com.monty.exam.mapper.UserDbMapper;
import com.monty.exam.otp.message.service.MessagePublisher;
import com.monty.exam.otp.service.OtpService;
import com.monty.exam.repository.UserRepository;
import com.monty.exam.user.service.AuthService;

public class AuthServiceImpl implements AuthService {
	private UserRepository userRepository;
	private OtpService otpService;
	private MessagePublisher messagePublisher;
    private AuthenticationManager authenticationManager;
    
	@Override
	public User register(User user) {

		UserDbModel userToDbUser = UserDbMapper.UserToDbUser(user);

		UserDbModel save = userRepository.save(userToDbUser);

		String otp = otpService.generateAndStoreOtp(user.getEmail());
		messagePublisher.sendOtp(user.getEmail(), otp);

		return UserDbMapper.dbUserToUser(save);
	}

	@Override
	public boolean verifyOtp(String email, String otp) {
        Optional<UserDbModel> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
        	 throw new RuntimeException("");
        }
        
        boolean isValid = otpService.validateOtp(email, otp);
        if (isValid) {
        	UserDbModel dbUser = optionalUser.get();
        	dbUser.setActive(true);
            userRepository.save(dbUser);
            return true;
        }
        return false;}

	@Override
	public String login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, email)
            );

        UserDbModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

          return null;
        }

}
