package com.pedrovalverde.vacunacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "vaccination_type")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class VaccinationType implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_vaccination_type",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Size(min = 3, max = 25)
    @NotNull
    @Column(name = "name", nullable = false, length = 25)
    @With
    private String name;

    @Size(min = 3, max = 100)
    @NotNull
    @Column(name = "description", nullable = false, length = 100)
    @With
    private String description;

    @NotNull
    @Column(name = "status", nullable = false, length = 1)
    @With
    private Boolean status;

    @Tolerate
    public VaccinationType(){
        super();
    }

    @PrePersist
    private void prePersist(){
        this.status = true;
    }

}
