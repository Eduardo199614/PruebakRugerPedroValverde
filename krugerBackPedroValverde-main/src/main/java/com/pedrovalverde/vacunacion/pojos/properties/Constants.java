package com.pedrovalverde.vacunacion.pojos.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    @Value("${jwt.signing-key:cp/Apx6S6fFEc6sC65nxWg==}")
    public String SIGNING_KEY;

    @Value("${jwt.token-prefix:Bearer }")
    public String TOKEN_PREFIX;

    @Value("${jwt.header-string:Authorization}")
    public String HEADER_STRING;

    @Value("${jwt.inactivity-key:inactivity}")
    public String INACTIVITY_KEY;

    public String getSIGNING_KEY() {
        return this.SIGNING_KEY;
    }

    public void setSIGNING_KEY(String SIGNING_KEY) {
        this.SIGNING_KEY = SIGNING_KEY;
    }

    public String getTOKEN_PREFIX() {
        return this.TOKEN_PREFIX;
    }

    public void setTOKEN_PREFIX(String TOKEN_PREFIX) {
        this.TOKEN_PREFIX = TOKEN_PREFIX;
    }

    public String getHEADER_STRING() {
        return this.HEADER_STRING;
    }

    public void setHEADER_STRING(String HEADER_STRING) {
        this.HEADER_STRING = HEADER_STRING;
    }

    public String getINACTIVITY_KEY() {
        return this.INACTIVITY_KEY;
    }

    public void setINACTIVITY_KEY(String INACTIVITY_KEY) {
        this.INACTIVITY_KEY = INACTIVITY_KEY;
    }
}
