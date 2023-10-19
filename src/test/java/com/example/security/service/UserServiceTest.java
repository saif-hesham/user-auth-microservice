package com.example.security.service;

import com.example.security.dao.UserRepository;
import com.example.security.entity.AppUser;
import com.example.security.exceptions.UserAuthException;
import com.example.security.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;



    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void checkUser_ValidCredentials_ReturnsToken() throws UserAuthException {
        String password = "12345678";
        String email = "saiff@gmail.com";
        String encodedPassword = "$2a$10$rlu7WHSlkjpIb9jovELbu.eho.HqoBnH1kK6JVZgdhsKZOIeIXARK";
        AppUser user = AppUser
                .builder()
                .email(email).
                password(encodedPassword).build();
        AppUser loggingUser = AppUser
                .builder()
                .email(email).
                password(password).build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("token");

        String token = userService.checkUser(loggingUser);

        assertEquals("token", token);
    }

    @Test
    void checkUser_InvalidCredentials_ThrowsUserAuthException() throws UserAuthException {
        String password = "123456";
        String email = "saiff@gmail.com";
        String encodedPassword = "$2a$10$rlu7WHSlkjpIb9jovELbu.eho.HqoBnH1kK6JVZgdhsKZOIeIXARK";
        AppUser user = AppUser
                .builder()
                .email(email).
                password(encodedPassword).build();
        AppUser loggingUser = AppUser
                .builder()
                .email(email).
                password(password).build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        assertThrows(UserAuthException.class, () -> userService.checkUser(loggingUser));
    }

    @Test
    void checkUser_UserNotFound_ThrowsUserAuthException() {
        String email = "test@example.com";
        AppUser user = AppUser.builder().email(email).build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        assertThrows(UserAuthException.class, () -> userService.checkUser(user));
    }

    @Test
    void addUser_NewUser_ReturnsToken() throws UserAuthException {
        String password = "123456";
        String email = "saiff@gmail.com";
        String encodedPassword = "$2a$10$rlu7WHSlkjpIb9jovELbu.eho.HqoBnH1kK6JVZgdhsKZOIeIXARK";
        AppUser user = AppUser
                .builder()
                .email(email).
                password(encodedPassword).build();
        AppUser loggingUser = AppUser
                .builder()
                .email(email).
                password(password).build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(userRepository.save(any(AppUser.class))).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn("token");


        String token = userService.addUser(loggingUser);


        assertEquals("token", token);

    }
    @Test
    void addUser_UserAlreadyExists_ThrowsUserAuthException() {
        String email = "test@example.com";
        AppUser existingUser = AppUser.builder().email(email).build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
        assertThrows(UserAuthException.class, () -> userService.addUser(existingUser));
    }
}
