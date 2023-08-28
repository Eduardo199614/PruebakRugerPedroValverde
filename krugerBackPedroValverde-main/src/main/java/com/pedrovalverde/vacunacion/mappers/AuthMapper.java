package com.pedrovalverde.vacunacion.mappers;

import com.pedrovalverde.vacunacion.entities.Auth;
import com.pedrovalverde.vacunacion.entities.Employee;
import com.pedrovalverde.vacunacion.pojos.RegisterEmployeeINP;

import java.util.UUID;

/**
 * Mapping entity Auth
 */
public class AuthMapper {
    public static Auth toEntity(UUID employeeId, String userName, String password){
        return Auth.builder()
                .idEmployee(employeeId)
                .username(userName)
                .password(password)
                .status(true)
                .build();
    }
}
