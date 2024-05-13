package com.platinum.timetapbe.service;


import com.platinum.timetapbe.dto.LoginRequest;
import com.platinum.timetapbe.dto.LoginResponse;
import com.platinum.timetapbe.dto.RegisterRequest;
import com.platinum.timetapbe.dto.RegisterResponse;

public interface IAuthService {
    RegisterResponse createUser(RegisterRequest user);

    RegisterResponse createAdmin(RegisterRequest user);

    LoginResponse login(LoginRequest loginRequest);

}
