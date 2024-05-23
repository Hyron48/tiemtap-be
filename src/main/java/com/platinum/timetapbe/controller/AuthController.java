package com.platinum.timetapbe.controller;

import com.platinum.timetapbe.dto.LoginRequest;
import com.platinum.timetapbe.dto.LoginResponse;
import com.platinum.timetapbe.dto.RegisterRequest;
import com.platinum.timetapbe.dto.RegisterResponse;
import com.platinum.timetapbe.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/test")
    public void test(){
        System.out.println("work test");
    }
}
