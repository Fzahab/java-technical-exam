package com.monty.exam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monty.exam.db.model.UserDbModel;

@Repository
public interface UserRepository extends JpaRepository<UserDbModel, Long>  {

	 Optional<UserDbModel> findByEmail(String email);
}
