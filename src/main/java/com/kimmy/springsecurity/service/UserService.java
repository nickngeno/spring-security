package com.kimmy.springsecurity.service;

import com.kimmy.springsecurity.model.UserInfo;
import com.kimmy.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfo addUser (UserInfo user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }
}
