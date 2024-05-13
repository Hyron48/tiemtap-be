package com.platinum.timetapbe.service;

import com.platinum.timetapbe.documents.User;
import com.platinum.timetapbe.dto.RegisterRequest;
import com.platinum.timetapbe.dto.RegisterResponse;

public interface IUserService {
    RegisterResponse createUser(RegisterRequest uRequest, String role);

    User getUserFromEmail(String email);
}
