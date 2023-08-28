package com.pedrovalverde.vacunacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrovalverde.vacunacion.entities.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role, Serializable> {
    List<Role> findByStatus(Boolean status);

    Optional<Role> findByName(String name);
}
