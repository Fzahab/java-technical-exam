package com.monty.exam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monty.exam.db.model.UserDbModel;

public interface UserRepository extends JpaRepository<UserDbModel, String>  {

	 Optional<UserDbModel> findByEmail(String email);
}
