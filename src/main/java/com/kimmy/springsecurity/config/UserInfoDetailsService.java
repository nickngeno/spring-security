package com.kimmy.springsecurity.config;

import com.kimmy.springsecurity.model.UserDetailsInfo;
import com.kimmy.springsecurity.model.UserInfo;
import com.kimmy.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo byName = userRepository.findByName(username);
        if (null == byName){
             throw new UsernameNotFoundException("User with username "+ username + "not found!");
        }
        return new UserDetailsInfo(byName);
    }
}
