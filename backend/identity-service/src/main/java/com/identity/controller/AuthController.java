package com.identity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identity.dto.AuthRequest;
import com.identity.dto.RegistrationDto;
import com.identity.entity.User;
import com.identity.service.AuthService;
import com.identity.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserService userService;
   
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto registrationDto) {
    	return authService.createNewUser(registrationDto);
    }
    
    @GetMapping("/get-user/{username}")
    public User getUser(@PathVariable String username){
    	return userService.findByUsername(username).get();
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
    	return authService.createAuthToken(authRequest);
    }
}