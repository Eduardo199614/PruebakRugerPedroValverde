package com.pedrovalverde.vacunacion.mappers;

import com.pedrovalverde.vacunacion.entities.Address;
import com.pedrovalverde.vacunacion.pojos.UpdateEmployeeINP;

/**
 * Mapping entity employee address
 */
public class EmployeeAddressMapper {

    /**
     * Cast Request to entity
     *
     * @param address Request
     * @return Entity Employee address
     */

    public static Address toEntity(UpdateEmployeeINP.Address address){
        return Address.builder()
                .id(address.getAddressId())
                .idEmployee(address.getEmployee())
                .oneStreet(address.getOneStreet())
                .twoStreet(address.getTwoStreet())
                .houseNumber(address.getHouseNumber())
                .reference(address.getReference())
                .build();
    }

}
