package com.security.springSecurity.controller;


import com.security.springSecurity.jwt.jwtUtil;
import com.security.springSecurity.model.Student;
import com.security.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    jwtUtil util;

    @GetMapping("/public/welcome")
    public String welcome(){
        return "Welcome User";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Student student) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        student.getName(),
                        student.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = util.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(
                Map.of("token", jwt)
        );
    }
}
