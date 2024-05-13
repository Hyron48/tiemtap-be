package com.platinum.timetapbe.dto;

import com.platinum.timetapbe.documents.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class RegisterResponse {
    private String firstName;
    private String lastName;
    private String email;
    private boolean verified;
    private Collection<Role> roles;
}
