package com.study.springsecurity.user.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetils implements UserDetails {

    private Users users;

    public MyUserDetils(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return users.getEnabled() == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return users.getEnabled() == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return users.getEnabled() == 1;
    }

    @Override
    public boolean isEnabled() {
        return users.getEnabled() == 1;
    }
}
