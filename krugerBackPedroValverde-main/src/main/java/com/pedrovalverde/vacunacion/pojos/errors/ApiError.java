package com.pedrovalverde.vacunacion.pojos.errors;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ApiError implements Serializable {
    private String code;
    private String message;

    public ApiError(){}

    public ApiError(String message){
        this.message = message;
        this.code = "-2";
    }

    public ApiError(String code, String message){
        this.code = code;
        this.message = message;
    }
}
