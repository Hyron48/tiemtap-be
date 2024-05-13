package com.platinum.timetapbe.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Document
public class Privilege {

    @JsonIgnore
    @Id
    private String id;

    private String name;

    @JsonIgnore
    @DBRef
    @ToString.Exclude
    private Collection<Role> roles;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

