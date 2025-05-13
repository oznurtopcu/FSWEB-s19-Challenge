package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.dto.LoginRequest;
import com.workintech.s19challenge.dto.RegisterRequest;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationService authenticationService, AuthenticationManager authenticationManager) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest) {
        return authenticationService.register(registerRequest.userName(), registerRequest.email(), registerRequest.password());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        //try-catch authentication managerın direkt olarak exception fırlatmasını önlüyor
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Başarıyla giriş yapıldı");

        }catch(BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Giriş bilgileri hatalı!");
        }
    }
}
