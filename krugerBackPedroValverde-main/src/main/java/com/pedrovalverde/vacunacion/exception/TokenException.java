package com.pedrovalverde.vacunacion.exception;

import com.pedrovalverde.vacunacion.pojos.errors.ApiError;

public class TokenException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private ApiError apiError;

    public TokenException(ApiError apiError){
        this.apiError = apiError;
    }

    public ApiError getApiError(){
        return this.apiError;
    }
}
