package com.pedrovalverde.vacunacion.service.impl;

import com.pedrovalverde.vacunacion.entities.Auth;
import com.pedrovalverde.vacunacion.exception.AuthException;
import com.pedrovalverde.vacunacion.pojos.AuthINP;
import com.pedrovalverde.vacunacion.pojos.errors.ApiError;
import com.pedrovalverde.vacunacion.repositories.AuthRepository;
import com.pedrovalverde.vacunacion.security.JwtTokenProvider;
import com.pedrovalverde.vacunacion.service.AuthService;
import com.pedrovalverde.vacunacion.service.tools.Tools;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class AuthServiceImpl extends Tools implements AuthService {

    @Autowired
    private AuthenticationManager authenticateManager;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Map<String,Object> autenticate(AuthINP auth) throws Exception {
        SecurityContextHolder.getContext().setAuthentication(this.authenticateUser(auth));

        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generateToken(auth.getUsername(), 1200);

        Map<String,Object> res = new LinkedHashMap<>();
        res.put("token", token);
        return res;
    }

    private Authentication authenticateUser(AuthINP auth) throws AuthException {
        try {
            Auth authOpt = authRepository.findByUsername(auth.getUsername())
                                            .orElseThrow(this::generateErrorNotFoundAuth);
			if(!authOpt.getStatus()) {
				throw new AuthException(new ApiError("Tu usuario no se encuentra disponible para transaccionar"));
			}
            return authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        }catch(BadCredentialsException e) {
            throw new AuthException(new ApiError("El usuario y/o contraseña son incorrectos"));
        }catch(UsernameNotFoundException user) {
            throw new AuthException(new ApiError("El usuario y/o contraseña son incorrectos"));
        }

    }

}
