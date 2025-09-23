package com.study.springsecurity.user.controller;

import com.study.springsecurity.user.entity.Users;
import com.study.springsecurity.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    // 사용자 프로필 페이지
    @GetMapping("/login/userProfile")
    public String userProfile(Model model) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // 사용자 정보를 서비스로부터 가져옴
        Users user = userService.getUserByUsername(username);

        // 모델에 사용자 정보 추가
        model.addAttribute("user", user);

        return "login/userProfile";
    }

}

