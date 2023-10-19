package com.example.security.rest;

import com.example.security.entity.AppUser;
import com.example.security.exceptions.UserAuthException;
import com.example.security.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserServiceImpl userService;

    @InjectMocks()
    private UserController userController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    public void addUser_ValidUser_ReturnsCreatedResponse() throws UserAuthException {
        AppUser newUser = mock(AppUser.class);
        when(userService.addUser(any(AppUser.class))).thenReturn("test_token");
        ResponseEntity<String> response = userController.addUser(newUser);
        verify(userService).addUser(newUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("test_token", response.getBody());
    }

    @Test
    public void checkUser_ValidUser_ReturnsOkResponse() throws UserAuthException {
        AppUser user = mock(AppUser.class);
        when(userService.checkUser(user)).thenReturn("test_token");
        ResponseEntity<String> response = userController.checkUser(user);
        verify(userService).checkUser(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("test_token", response.getBody());
    }
}
