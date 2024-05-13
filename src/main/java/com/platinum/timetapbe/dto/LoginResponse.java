package com.platinum.timetapbe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LoginResponse {
    private String jwt;
    private String refreshToken;
    private List<String> userPermissions = new ArrayList<>();
}