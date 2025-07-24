package com.monty.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.monty.exam.core.model.User;
import com.monty.exam.db.model.UserDbModel;
import com.monty.exam.mapper.UserDbMapper;
import com.monty.exam.repository.UserRepository;
import com.monty.exam.user.service.impl.UserServiceImpl;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("plainPassword");

        UserDbModel dbModel = new UserDbModel();
        dbModel.setEmail("test@example.com");

        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

        try (MockedStatic<UserDbMapper> mapper = mockStatic(UserDbMapper.class)) {
            mapper.when(() -> UserDbMapper.toDbUser(any(User.class))).thenReturn(dbModel);
            mapper.when(() -> UserDbMapper.toUser(dbModel)).thenReturn(user);

            when(userRepository.save(dbModel)).thenReturn(dbModel);

            User savedUser = userService.save(user);

            assertEquals("test@example.com", savedUser.getEmail());
            assertTrue(savedUser.getCreatedAt() != null);
            assertFalse(savedUser.getActive());

            verify(userRepository).save(dbModel);
        }
    }

    @Test
    void testFindByEmail() {
        String email = "test@example.com";

        UserDbModel dbModel = new UserDbModel();
        dbModel.setEmail(email);

        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(dbModel));

        try (MockedStatic<UserDbMapper> mapper = mockStatic(UserDbMapper.class)) {
            mapper.when(() -> UserDbMapper.toUser(dbModel)).thenReturn(user);

            User result = userService.findByEmail(email);

            assertNotNull(result);
            assertEquals(email, result.getEmail());

            verify(userRepository).findByEmail(email);
        }
    }

    @Test
    void testFindByEmail_UserNotFound() {
        String email = "notfound@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.findByEmail(email));
        verify(userRepository).findByEmail(email);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setEmail("update@example.com");

        UserDbModel dbModel = new UserDbModel();
        dbModel.setEmail("update@example.com");

        User updatedUser = new User();
        updatedUser.setEmail("update@example.com");
        updatedUser.setActive(true);

        when(userRepository.save(dbModel)).thenReturn(dbModel);

        try (MockedStatic<UserDbMapper> mapper = mockStatic(UserDbMapper.class)) {
            mapper.when(() -> UserDbMapper.toDbUser(user)).thenReturn(dbModel);
            mapper.when(() -> UserDbMapper.toUser(dbModel)).thenReturn(updatedUser); 

            User result = userService.update(user);

            assertEquals("update@example.com", result.getEmail());
            assertTrue(result.getActive()); 
            assertNotSame(user, result); 
            verify(userRepository).save(dbModel);
        }
    }
}
