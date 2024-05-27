package com.platinum.timetapbe.controller;

import com.platinum.timetapbe.dto.*;
import com.platinum.timetapbe.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register-user")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest user) {
        return new ResponseEntity<>(authService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<RegisterResponse> registerAdmin(@Valid @RequestBody RegisterRequest user) {
        return new ResponseEntity<>(authService.createAdmin(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest tokenRequest){
        return new ResponseEntity<>(new JwtResponse(authService.refreshToken(tokenRequest)), HttpStatus.OK);
    }
}
