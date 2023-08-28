package com.pedrovalverde.vacunacion.pojos;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class AuthINP implements Serializable {

    @With
    private UUID id;

    @With
    private UUID idEmployee;

    @NotEmpty(message = "No se encontro el usuario")
    @Size(min = 3, max = 150)
    @With
    private String username;

    @NotEmpty(message = "No se encontro ninguna contraseña")
    @Size(min = 3, max = 25)
    @With
    private String password;

    @Tolerate
    public AuthINP(){super();}

}
