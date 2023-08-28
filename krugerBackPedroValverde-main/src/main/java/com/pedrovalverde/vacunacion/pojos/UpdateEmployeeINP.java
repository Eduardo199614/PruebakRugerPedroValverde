package com.pedrovalverde.vacunacion.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pedrovalverde.vacunacion.utils.Validations;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class UpdateEmployeeINP implements Serializable {

    @JsonProperty("employee_id")
    @With
    private UUID employeeId;

    @With
    private String username;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Fecha Nacimiento no puede ser nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Validations.DATE_FORMAT)
    @JsonProperty("fecha_nacimiento")
    @With
    private Date birthDate;

    @NotNull(message = "Estado de vacunacion no puede estar vacio")
    @JsonProperty("estado_vacunacion")
    @With
    private Boolean vaccinationStatus;

    @Valid
    @JsonProperty("celulares")
    @With
    Set<PhoneMobile> phones;

    @Valid
    @JsonProperty("direcciones")
    @With
    Set<Address> addresses;

    @Valid
    @JsonProperty("vacunas")
    @With
    Set<VaccinationEmployee> vaccinationEmployees;

    @Builder
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    public static class Address implements Serializable {

        @JsonProperty("address_id")
        @With
        private UUID addressId;

        @JsonProperty("empleado_id")
        @With
        private UUID employee;

        @JsonProperty("calle_principal")
        @With
        private String oneStreet;

        @JsonProperty("calle_secundaria")
        @With
        private String twoStreet;

        @JsonProperty("numero_casa")
        @With
        private String houseNumber;

        @NotNull(message = "La referencia debe contener informacion")
        @Size(min = 3, max = 250, message = "La referencia debe contener minimo 3 caracteres y maximo 250")
        @JsonProperty("referencia")
        @With
        private String reference;

        @Tolerate
        Address(){
            super();
        }
    }

    @Builder
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    public static class PhoneMobile implements Serializable {

        @JsonProperty("phone_id")
        @With
        UUID phoneId;

        @JsonProperty("empleado_id")
        @With
        private UUID employee;

        @Size(min = 7, max = 25, message = "El numero celular debe contener al menos 7 dijitos y maximo 25")
        @JsonProperty("numero_celular")
        @With
        private String numberPhone;

        @Size(min = 3, max = 150, message = "La descripcion debe contener al menos 3 caracteres y maximo 150")
        @JsonProperty("descripcion")
        @With
        private String description;

        @Tolerate
        PhoneMobile() {
            super();
        }
    }

    @Builder
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    public static class VaccinationEmployee implements Serializable {

        @JsonProperty("vaccination_id")
        @With
        UUID vaccinationId;

        @JsonProperty("empleado_id")
        @With
        private UUID employee;

        @JsonProperty("tipo_vacuna_id")
        @With
        private UUID vaccineType;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Validations.DATE_FORMAT)
        @JsonProperty("fecha_vacuna")
        @With
        private Date vaccinationDate;

        @JsonProperty("numero_dosis")
        @NotNull(message = "numero_dosis no puede estar vacio")
        @With
        private Integer numberDoses;

    }
}