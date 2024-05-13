package com.platinum.timetapbe.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoInfo {
    private String name;
    private String type;
    private byte[] imageData;
}
