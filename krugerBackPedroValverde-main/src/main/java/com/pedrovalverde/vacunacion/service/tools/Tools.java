package com.pedrovalverde.vacunacion.service.tools;

import com.pedrovalverde.vacunacion.exception.GeneralException;
import com.pedrovalverde.vacunacion.pojos.errors.ApiError;

import org.springframework.stereotype.Service;

@Service
public class Tools {
 
    protected GeneralException generateErrorEmployee(String message) {
        return new GeneralException(new ApiError(message));
    }

    protected GeneralException generateErrorNotFoundEmployee() {
        return new GeneralException(new ApiError("No se encontro el empleado o esta inactivo"));
    }

    protected GeneralException generateErrorNotFoundVaccinateType() {
        return new GeneralException(new ApiError("No se encontro la vacuna seleccionada"));
    }

    protected GeneralException generateErrorNotFoundRole() {
        return new GeneralException(new ApiError("No se encontro el Rol seleccionado"));
    }

    protected GeneralException generateErrorNotFoundAuth() {
        return new GeneralException(new ApiError("Usuario y/o contraseña incorrectas"));
    }
}
