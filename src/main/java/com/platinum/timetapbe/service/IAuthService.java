package com.platinum.timetapbe.service;


import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.dto.*;

public interface IAuthService {
    RegisterResponse createUser(RegisterRequest user);

    RegisterResponse createAdmin(RegisterRequest user);

    LoginResponse login(LoginRequest loginRequest);

    String refreshToken(RefreshTokenRequest refreshTokenRequest);

    void logout(User user);
}
