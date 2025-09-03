package com.study.springsecurity.user.controller;

import com.study.springsecurity.user.entity.User;
import com.study.springsecurity.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/login/page")
    public String loginPage() {
        return "user/loginPage";
    }

    @PostMapping("/login/loginProc")
    public String loginProc(User user) {

        logger.info("loginProc - start");
        logger.info("user id : " + user.getId());

        return "main";
    }

}

