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

    public String createUser(Users signupUser){

        Users users = new Users();

        users.setUsername(signupUser.getUsername());
        users.setPassword(passwordEncoder.encode(signupUser.getPassword()));
        users.setEnabled(signupUser.getEnabled());

        Users result = userRepository.save(users);

        return result.getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepository.findUserByUsername(username).orElse(null);

        if(users != null){
            return new MyUserDetils(users);
        }

        return null;
    }
}
