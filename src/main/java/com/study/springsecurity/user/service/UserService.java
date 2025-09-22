package com.study.springsecurity.user.service;

import com.study.springsecurity.user.entity.MyUserDetils;
import com.study.springsecurity.user.entity.Users;
import com.study.springsecurity.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users createUser(Users signupUser) {

        Users users = new Users();

        users.setUsername(signupUser.getUsername());
        users.setPassword(passwordEncoder.encode(signupUser.getPassword()));
        users.setEnabled(signupUser.getEnabled());

        return userRepository.save(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new MyUserDetils(users);
    }

    public Users modifyPassword(Users users) {
        Users existingUser = userRepository.findUserByUsername(users.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("No user: " + users.getUsername()));

        existingUser.setPassword(passwordEncoder.encode(users.getPassword()));

        return userRepository.save(existingUser);
    }
}
