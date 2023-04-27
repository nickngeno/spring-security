package com.kimmy.springsecurity.controller;


import com.kimmy.springsecurity.model.UserInfo;
import com.kimmy.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/v1/user")
    public UserInfo addUser (@RequestBody  UserInfo user) {
        return userService.addUser(user);
    }

}
