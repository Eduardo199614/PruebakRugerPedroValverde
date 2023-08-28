package com.pedrovalverde.vacunacion.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "roles")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Role implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_rol",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Size(min = 3, max = 25)
    @Column(name = "name", length = 25)
    @With
    private String name;

    @Size(min = 3, max = 150)
    @Column(name = "description", length = 150)
    @With
    private String description;

    @Column(name = "status", length = 1)
    @With
    private Boolean status;

    @Tolerate
    public Role() {
        super();
    }
}
