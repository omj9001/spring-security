package com.study.springsecurity.user.service;

import com.study.springsecurity.user.dto.SignupUser;
import com.study.springsecurity.user.entity.User;
import com.study.springsecurity.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(SignupUser sUser){

        User user = new User();
        user.setUsername(sUser.getUsername());
        user.setPassword(passwordEncoder.encode(sUser.getPassword()));
        user.setEmail(sUser.getEmail());

        User result = userRepository.save(user);

        return result.getUsername();
    }
}
