package com.pedrovalverde.vacunacion.entities.audit;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.pedrovalverde.vacunacion.security.UserInfoAuthentication;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class Audit implements Serializable {

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        createdOn = new Date();
        createdBy = UserInfoAuthentication.getUsername();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = new Date();
        updatedBy = UserInfoAuthentication.getUsername();
    }

    @Tolerate
    public Audit(){
        super();
    }
}
