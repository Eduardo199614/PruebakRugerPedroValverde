package com.pedrovalverde.vacunacion.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pedrovalverde.vacunacion.utils.Validations;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class RegisterEmployeeINP implements Serializable {

    @JsonProperty("id")
    @With
    private UUID id;

    @Size(min = 10,max = 10, message = "La cedula debe contener 10 caracteres")
    @NotNull(message = "La cedula no puede estar vacio")
    @JsonProperty("cedula")
    @With
    private String identification;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @JsonProperty("nombres")
    @Pattern(regexp = Validations.REGEX_NAME_VALIDATION, message = "Nombre no Valido")
    @With
    private String names;

    @NotEmpty(message = "El apellido no puede ser vacio")
    @JsonProperty("apellidos")
    @Pattern(regexp = Validations.REGEX_NAME_VALIDATION, message = "Apellido no Valido")
    @With
    private String lastname;

    @NotEmpty(message = "El Correo no puede estar vacio")
    @Size(min = 5, max = 250, message = "El correo debe contener entre 5 a 250 caracteres")
    @Pattern(regexp = Validations.REGEX_MAIL_VALIDATION, message = "Correo no valido")
    @JsonProperty("correo")
    @With
    private String email;

    @Tolerate
    RegisterEmployeeINP() {
        super();
    }
}

