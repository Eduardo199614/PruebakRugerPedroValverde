package com.pedrovalverde.vacunacion.pojos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class JwtINP implements Serializable {

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtINP(String tokenDeAcceso) {
        super();
        this.accessToken = tokenDeAcceso;
    }

    public JwtINP(String accessToken, String tokenType) {
        super();
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
}
