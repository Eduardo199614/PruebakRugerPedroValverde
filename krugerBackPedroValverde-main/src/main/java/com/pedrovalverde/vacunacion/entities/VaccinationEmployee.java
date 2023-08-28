package com.pedrovalverde.vacunacion.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "vaccination_employee")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
//TODO: UNA PERSONA PUEDE TENER PUESTA VARIAS VACUNAS DE DIFERENTES TIPOS
public class VaccinationEmployee implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_vaccination_employee",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "id_employee")
    @With
    private UUID idEmployee;
    @JsonIgnore
    @JoinColumn(name = "id_employee",
            referencedColumnName = "id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_employee"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    @NotNull
    @Column(name = "id_vaccination_type")
    @With
    private UUID idVaccinationType;
    @JsonIgnore
    @JoinColumn(name = "id_vaccination_type",
            referencedColumnName = "id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_vaccination_type"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VaccinationType vaccinationType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "vaccination_date")
    @With
    private Date vaccinationDate;

    @Column(name = "number_doses", nullable = false, length = 1)
    @With
    private Integer numberDoses;

    @JsonIgnore
    @Column(name = "status", length = 1)
    @With
    private Boolean status;

    @Tolerate
    public VaccinationEmployee(){
        super();
    }

    @PrePersist
    public void prePersist() {
        this.status = true;
    }
}
