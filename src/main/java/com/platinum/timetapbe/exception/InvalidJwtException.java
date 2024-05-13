package com.platinum.timetapbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidJwtException extends RuntimeException{
    public InvalidJwtException(String message){
        super(message);
    }
}
