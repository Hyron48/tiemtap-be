package com.platinum.timetapbe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenStringHelper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encode(String password){
        return passwordEncoder.encode(password);
    }

    public String createStringToken(){
        return UUID.randomUUID().toString();
    }
}
