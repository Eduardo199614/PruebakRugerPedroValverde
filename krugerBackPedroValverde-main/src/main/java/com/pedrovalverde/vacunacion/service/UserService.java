package com.pedrovalverde.vacunacion.service;

import com.pedrovalverde.vacunacion.entities.Auth;
import com.pedrovalverde.vacunacion.entities.Role;
import com.pedrovalverde.vacunacion.exception.GeneralException;
import com.pedrovalverde.vacunacion.pojos.errors.ApiError;
import com.pedrovalverde.vacunacion.repositories.AuthRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Auth auth = authRepository.findByUsernameAndStatus(userName, true)
                .orElseThrow(() -> new GeneralException(new ApiError("Usuario y/o Contraseña es incorrecto")));
        
        return new User(auth.getUsername(), auth.getPassword(), mappedRole(auth.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mappedRole(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
