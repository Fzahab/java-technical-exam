package com.monty.exam.user.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Autowired
    private UserRepository userRepository;
   
    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public User findById(Long userId) {
    	
    	Optional<UserDbModel> dbUser = userRepository.findById(userId);
    	if(dbUser.isEmpty()) {
    		throw new  UsernameNotFoundException("User not found");
    	}
    	
    	User user = UserDbMapper.toUser(dbUser.get());
    	
        return user;
    }

    @Override
    public User save(User user) {
    	
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
    	Optional<UserDbModel> dbUser = userRepository.findByEmail(email);
    	if(dbUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
    	}
		User user = UserDbMapper.toUser(dbUser.get());
    	
        return user;
    }

	@Override
	public User update(User user) {
		UserDbModel userToDbUser = UserDbMapper.toDbUser(user);
		UserDbModel updatedUser = userRepository.save(userToDbUser);
		
        return UserDbMapper.toUser(updatedUser); 
    }
}
