package com.pedrovalverde.vacunacion.mappers;

import com.pedrovalverde.vacunacion.entities.MobilePhone;
import com.pedrovalverde.vacunacion.pojos.UpdateEmployeeINP;

public class EmployeePhoneMapper {

    
    public static MobilePhone toEntity(UpdateEmployeeINP.PhoneMobile phone){
        return MobilePhone.builder()
                .id(phone.getPhoneId())
                .idEmployee(phone.getEmployee())
                .numberPhone(phone.getNumberPhone())
                .description(phone.getDescription())
                .build();
    }

}
