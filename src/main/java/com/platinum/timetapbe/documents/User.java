package com.platinum.timetapbe.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platinum.timetapbe.dto.PhotoInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Document
public class User {

    @Id
    private String id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Indexed(unique = true)
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private boolean verified;

    private String resetPasswordCode;

    private Date resetPCreationDate;

    private Date resetPExpiredDate;

    private PhotoInfo avatar;

    @JsonIgnore
    @DBRef
    private Collection<Role> roles;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
