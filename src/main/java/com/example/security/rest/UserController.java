package com.example.security.rest;

import com.example.security.entity.AppUser;
import com.example.security.exceptions.UserAuthException;
import com.example.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserServiceImpl userService;


    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody AppUser newUser) throws UserAuthException {
        String jwtToken = userService.addUser(newUser);
        return ResponseEntity.ok().body(jwtToken);

    }

    @PostMapping("/login")
    public ResponseEntity<String> checkUser(@RequestBody AppUser user) throws UserAuthException {
        String jwtToken = userService.checkUser(user);
        return ResponseEntity.ok().body(jwtToken);
    }

}
