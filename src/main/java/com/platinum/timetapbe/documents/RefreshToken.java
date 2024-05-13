package com.platinum.timetapbe.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Document
public class RefreshToken {

    @JsonIgnore
    @Id
    private String id;

    @NotBlank
    private String refreshToken;

    @DBRef
    private User user;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

