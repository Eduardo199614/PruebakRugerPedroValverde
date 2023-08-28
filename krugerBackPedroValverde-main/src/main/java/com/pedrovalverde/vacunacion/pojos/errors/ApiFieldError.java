package com.pedrovalverde.vacunacion.pojos.errors;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ApiFieldError implements Serializable {

    private String code;
    private List<String> messages;

    public ApiFieldError(List<String> messages){
        this.messages = messages;
        this.code = "-1";
    }

    public ApiFieldError(String code, List<String> messages){
        this.code = code;
        this.messages = messages;
    }

}
