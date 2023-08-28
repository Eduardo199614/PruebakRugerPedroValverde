package com.pedrovalverde.vacunacion.mappers;

import com.pedrovalverde.vacunacion.entities.VaccinationEmployee;
import com.pedrovalverde.vacunacion.pojos.UpdateEmployeeINP;


public class EmployeeVaccinationMapper {
   
    public static VaccinationEmployee toEntity(UpdateEmployeeINP.VaccinationEmployee vaccination){
        return VaccinationEmployee.builder()
                .id(vaccination.getVaccinationId())
                .idEmployee(vaccination.getEmployee())
                .idVaccinationType(vaccination.getVaccineType())
                .vaccinationDate(vaccination.getVaccinationDate())
                .numberDoses(vaccination.getNumberDoses())
                .build();
    }
}
