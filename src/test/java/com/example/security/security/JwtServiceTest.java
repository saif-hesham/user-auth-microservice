package com.example.security.security;

import com.example.security.entity.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;
    @Test
    public void generateToken_ValidUser_ReturnsToken() {
        AppUser user = mock(AppUser.class);
        when(user.getEmail()).thenReturn("test@example.com");
        String token = jwtService.generateToken(user);

        // Assert that the token is not null
        assertNotNull(token);
    }
}
