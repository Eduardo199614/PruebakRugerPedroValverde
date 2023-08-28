package com.pedrovalverde.vacunacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedrovalverde.vacunacion.entities.enums.VaccinationStatus;
import com.pedrovalverde.vacunacion.utils.Validations;
import com.pedrovalverde.vacunacion.entities.audit.Audit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@Entity
@Table(name = "employee")
//TODO: SE CREA LA TABLA VACCINATION_EMPLOYEE PORQUE UNA PERSONA PUEDE ESTAR VACUNADO CON DIFERENTES TIPOS DE VACUNAS
public class Employee extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_employee",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "identification", length = 10, nullable = false, unique = true)
    @With
    private String identification;

    @Column(name = "names", length = 100, nullable = false)
    @With
    private String names;

    @Column(name = "surname", length = 50, nullable = false)
    @With
    private String surname;

    @Column(name = "second_surname", length = 50)
    @With
    private String secondSurname;

    @Pattern(regexp = Validations.REGEX_MAIL_VALIDATION, message = "Email required")
    @Column(name = "email", length = 250, nullable = false)
    @With
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    @With
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "vaccination_status", length = 1)
    @With
    private VaccinationStatus vaccinationStatus;

    @Column(name = "status", length = 1)
    @JsonIgnore
    @With
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<Address> addresses = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<Auth> auths = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<MobilePhone> mobilePhones = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<VaccinationEmployee> vaccinationEmployees = new HashSet<>();

    @Tolerate
    public Employee() {
        super();
    }
}
