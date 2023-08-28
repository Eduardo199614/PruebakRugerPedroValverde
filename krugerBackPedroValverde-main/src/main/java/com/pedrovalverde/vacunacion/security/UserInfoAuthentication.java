package com.pedrovalverde.vacunacion.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class UserInfoAuthentication {
    //get the username of the person logged in
    public static String getUsername (){
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
        log.debug("Get username of logged User: {}", userDetails.getUsername());
        return !userDetails.getUsername().trim().isEmpty() ? userDetails.getUsername() : "";
    }
}