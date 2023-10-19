package com.example.security.service;

import com.example.security.entity.AppUser;
import com.example.security.exceptions.UserAuthException;

import java.util.List;

public interface UserService {
    String checkUser(AppUser curUser) throws UserAuthException;
    String addUser(AppUser newUser) throws UserAuthException;
}
