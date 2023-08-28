package com.pedrovalverde.vacunacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedrovalverde.vacunacion.entities.audit.Audit;

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
@Table(name = "auth_employee", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}) })
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Auth extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_auth_employee",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "id_employee")
    @With
    private UUID idEmployee;
    @JsonIgnore
    @JoinColumn(name = "id_employee",
            nullable = false,
            referencedColumnName = "id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_employee"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    @Size(min = 3, max = 150)
    @Column(name = "username", length = 150, unique = true)
    @With
    private String username;

    @Size(min = 3, max = 150)
    @Column(name = "password", length = 150)
    @With
    private String password;

    @NotNull
    @Column(name = "status", length = 1, nullable = false)
    @With
    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "auth_role",
            joinColumns = @JoinColumn(name = "auth_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id"))
    @With
    private Set<Role> roles = new HashSet<>();

    @Tolerate
    public Auth() {
        super();
    }
}
