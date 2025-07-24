package com.monty.exam.user.service;

import com.monty.exam.core.model.User;

public interface UserService {

    User save(User user);

    User update(User user);

    User findByEmail(String email);
}
