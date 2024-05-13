package com.platinum.timetapbe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "The firstName must not be empty")
    private String firstName;

    @NotBlank(message = "The lastName must not be empty")
    private String lastName;

    @NotBlank(message = "The email must not be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "The password must not be empty")
    @Size(min = 8, message = "Password size must have at least 8 characters")
    private String password;

    private boolean enabled = true;
}
