package com.platinum.timetapbe.dto;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class TagStampRequest {
    private String positionLabel;
    private ArrayList<Number> coordinates;
}
