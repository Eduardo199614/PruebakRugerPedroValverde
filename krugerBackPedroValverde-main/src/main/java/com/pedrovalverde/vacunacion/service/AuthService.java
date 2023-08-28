package com.pedrovalverde.vacunacion.service;

import java.util.Map;

import com.pedrovalverde.vacunacion.pojos.AuthINP;


public interface AuthService {

    Map<String, Object> autenticate(AuthINP auth) throws Exception;

}
