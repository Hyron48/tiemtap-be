package com.platinum.timetapbe.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Document
public class TagStamp {

    @Id
    private String id;

    @NotNull
    private String positionLabel;

    @NotNull
    private ArrayList<Number> coordinates;

    @NotNull
    private Date timeCode;

    @JsonIgnore
    @DBRef
    private User user;
}
