package com.pedrovalverde.vacunacion.mappers;

import com.pedrovalverde.vacunacion.entities.Employee;
import com.pedrovalverde.vacunacion.pojos.RegisterEmployeeINP;


public class EmployeeMapper {
    
    public static Employee toEntityRegister(RegisterEmployeeINP employee){
        String[] parts = employee.getLastname().trim().split(" ");
        String surname = "";
        String secondSurname = "";
        if(parts.length == 2){
            surname = parts[0];
            secondSurname = parts[1];
        }else {
            surname = employee.getLastname();
        }
        return Employee.builder()
                .id(employee.getId())
                .identification(employee.getIdentification())
                .names(employee.getNames())
                .surname(surname)
                .secondSurname(secondSurname)
                .email(employee.getEmail())
                .status(true)
                .build();
    }
}