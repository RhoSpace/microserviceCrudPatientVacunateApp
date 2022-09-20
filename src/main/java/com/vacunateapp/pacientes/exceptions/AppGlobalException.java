package com.vacunateapp.pacientes.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppGlobalException extends Exception {

    private final int responseCode;

    private final List<ErrorDto> errorsList = new ArrayList<>();

    public AppGlobalException(int responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}
