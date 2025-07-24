package com.monty.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.monty.exam.core.model.OneTimePassword;
import com.monty.exam.core.model.User;
import com.monty.exam.otp.message.service.MessagePublisher;
import com.monty.exam.otp.service.OtpService;
import com.monty.exam.security.JwtService;
import com.monty.exam.user.service.UserService;
import com.monty.exam.user.service.impl.AuthServiceImpl;

@SpringBootTest
class JavaTechnicalExamApplicationTests {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserService userService;

    @Mock
    private OtpService otpService;

    @Mock
    private MessagePublisher messagePublisher;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister_Success() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setId(1L);

        when(userService.save(any(User.class))).thenReturn(user);
        when(otpService.generateAndStoreOtp("test@example.com")).thenReturn("123456");

        User registeredUser = authService.register(user);

        assertEquals("test@example.com", registeredUser.getEmail());
        verify(userService).save(user);
        verify(otpService).generateAndStoreOtp("test@example.com");
        verify(messagePublisher).sendOtp(any(OneTimePassword.class));
    }

    @Test
    public void testVerifyOtp_Success() {
        String email = "test@example.com";
        String otp = "123456";

        User user = new User();
        user.setEmail(email);
        user.setActive(false);

        when(userService.findByEmail(email)).thenReturn(user);
        when(otpService.validateOtp(email, otp)).thenReturn(true);

        boolean result = authService.verifyOtp(email, otp);

        assertTrue(result);
        assertTrue(user.getActive());
        verify(userService).update(user);
    }

    @Test
    public void testVerifyOtp_InvalidOtp() {
        String email = "test@example.com";
        String otp = "000000";

        User user = new User();
        user.setEmail(email);

        when(userService.findByEmail(email)).thenReturn(user);
        when(otpService.validateOtp(email, otp)).thenReturn(false);

        boolean result = authService.verifyOtp(email, otp);

        assertFalse(result);
        verify(userService, never()).update(user);
    }

    @Test
    public void testLogin_Success() {
        String email = "test@example.com";
        String password = "password";
        String token = "jwt-token";

        User user = new User();
        user.setEmail(email);

        when(userService.findByEmail(email)).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(token);

        String result = authService.login(email, password);

        assertEquals(token, result);
        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(email, password));
        verify(jwtService).generateToken(user);
    }
}
