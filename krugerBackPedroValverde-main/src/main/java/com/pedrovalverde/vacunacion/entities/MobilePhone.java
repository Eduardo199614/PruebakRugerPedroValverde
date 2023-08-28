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
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
@Table(name = "mobile_phone")
public class MobilePhone implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_mobile_phone",
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

    @Size(min = 7, max = 25)
    @Column(name = "number_phone", nullable = false, length = 25)
    @With
    private String numberPhone;

    @Size(min = 3, max = 150)
    @Column(name = "description", length = 150)
    @With
    private String description;

    @Column(name = "status", nullable = false, length = 1)
    @With
    private Boolean status;

    @Tolerate
    public MobilePhone() {
        super();
    }

    @PrePersist
    public void prePersist() {
        this.status = true;
    }
}
