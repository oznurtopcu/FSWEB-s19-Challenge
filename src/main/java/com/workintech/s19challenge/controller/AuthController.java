package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.dto.RegisterUser;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterUser registerUser) {
        return authenticationService.register(registerUser.userName(), registerUser.email(), registerUser.password());
    }
}
