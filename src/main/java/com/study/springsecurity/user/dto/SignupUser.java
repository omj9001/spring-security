package com.study.springsecurity.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupUser {

    private String username;

    private String password;

    private String email;
}
