package com.monty.exam.user.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.monty.exam.core.model.User;
import com.monty.exam.db.model.UserDbModel;
import com.monty.exam.mapper.UserDbMapper;
import com.monty.exam.repository.UserRepository;
import com.monty.exam.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
   
    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public User save(User user) {
    	log.info("Saving User of email {} to Database",user.getEmail());
    	
    	String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		user.setCreatedAt(LocalDateTime.now());
		user.setActive(false);
		UserDbModel userToDbUser = UserDbMapper.toDbUser(user);

		UserDbModel savedUser = userRepository.save(userToDbUser);
		
        return UserDbMapper.toUser(savedUser); 
    }

    @Override
    public User findByEmail(String email) {
    	log.info("getting User of email {} from Database",email);

    	Optional<UserDbModel> dbUser = userRepository.findByEmail(email);
    	if(dbUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
    	}
		User user = UserDbMapper.toUser(dbUser.get());
    	
        return user;
    }

	@Override
	public User update(User user) {
    	log.info("Updating User of email {}",user.getEmail());

		UserDbModel userToDbUser = UserDbMapper.toDbUser(user);
		UserDbModel updatedUser = userRepository.save(userToDbUser);
		
        return UserDbMapper.toUser(updatedUser); 
    }
}
