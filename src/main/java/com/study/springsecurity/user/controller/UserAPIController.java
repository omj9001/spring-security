package com.study.springsecurity.user.controller;

import com.study.springsecurity.user.entity.Users;
import com.study.springsecurity.user.repository.UserRepository;
import com.study.springsecurity.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserAPIController {

    private static final Logger logger = LoggerFactory.getLogger(UserAPIController.class);
    private final UserRepository userRepository;

    private UserService userService;

    public UserAPIController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/join")
    public ResponseEntity joinUser(@RequestBody Users users){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        users.setPassword(encoder.encode(users.getPassword()));

        userService.createUser(users);

        logger.info("user name : " + users.getUsername());
        logger.info("user password : " + users.getPassword());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUsers")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

}

