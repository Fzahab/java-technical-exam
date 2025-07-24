package com.monty.exam.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monty.exam.core.model.User;
import com.monty.exam.db.model.UserDbModel;

public class UserDbMapper {
	private static final Logger log = LoggerFactory.getLogger(UserDbMapper.class);

	public static User toUser(UserDbModel dbModel) {
		log.info("Map user from db model to core model");
		User user = new User();
		user.setId(dbModel.getId());
		user.setName(dbModel.getName());
		user.setEmail(dbModel.getEmail());
		user.setPassword(dbModel.getPassword());
		user.setAge(dbModel.getAge());
		user.setActive(dbModel.isActive());
		user.setCreatedAt(dbModel.getCreatedAt());
		return user;
	}

	public static UserDbModel toDbUser(User coreModel) {
		log.info("Map user from core model to db model");

		UserDbModel dbUser = new UserDbModel();
		
		if(coreModel.getId() != null){	
			dbUser.setId(coreModel.getId());
		}
		
		dbUser.setName(coreModel.getName());
		dbUser.setEmail(coreModel.getEmail());
		dbUser.setPassword(coreModel.getPassword());
		dbUser.setAge(coreModel.getAge());
		dbUser.setActive(coreModel.getActive());
		dbUser.setCreatedAt(coreModel.getCreatedAt());
		return dbUser;
	}




}
