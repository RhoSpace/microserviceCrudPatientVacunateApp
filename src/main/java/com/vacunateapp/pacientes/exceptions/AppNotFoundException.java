package com.vacunateapp.pacientes.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppNotFoundException extends AppGlobalException{

    public AppNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }
}
