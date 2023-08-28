package com.pedrovalverde.vacunacion.controllers;

import com.pedrovalverde.vacunacion.commons.Commons;
import com.pedrovalverde.vacunacion.pojos.AuthINP;
import com.pedrovalverde.vacunacion.service.AuthService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public @ResponseBody
    ResponseEntity<Object> login(@Valid @RequestBody AuthINP authINP,
                                 Errors errors) throws Exception {
        Commons.validateFieldRequest(errors);
        log.debug("Inicio proceso de login: {}", authINP);
        Map<String, Object> responseMap;
        responseMap = authService.autenticate(authINP);
        return ResponseEntity.ok().body(responseMap);
    }

    private final String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
