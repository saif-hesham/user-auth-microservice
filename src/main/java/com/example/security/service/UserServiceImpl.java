package com.example.security.service;

import com.example.security.dao.UserRepository;
import com.example.security.entity.AppUser;
import com.example.security.exceptions.UserAuthException;
import com.example.security.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String checkUser(AppUser curUser) throws UserAuthException {
        Optional<AppUser> foundUser = userRepository.findByEmail(curUser.getEmail());
        if (foundUser.isPresent()) {
            AppUser existingUser = foundUser.get();
            if (bCryptPasswordEncoder.matches(curUser.getPassword(), existingUser.getPassword())) {
                return jwtService.generateToken(existingUser);
            } else {
                throw new UserAuthException("Password isn't correct");
            }
        } else {
            throw new UserAuthException("User with this email doesn't exist");
        }

    }


    @Override
    public String addUser(AppUser newUser) throws UserAuthException {

        Optional<AppUser> user = userRepository.findByEmail(newUser.getEmail());
        if (user.isPresent()) {
            throw new UserAuthException("This user already exists, try again with login");
        } else {
            AppUser tempUser = new AppUser();
            tempUser.setEmail(newUser.getEmail());
            tempUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            AppUser addedUser = userRepository.save(tempUser);
            return jwtService.generateToken(addedUser);
        }
    }


}
