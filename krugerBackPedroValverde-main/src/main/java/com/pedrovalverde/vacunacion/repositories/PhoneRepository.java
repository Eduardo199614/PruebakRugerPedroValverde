package com.pedrovalverde.vacunacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrovalverde.vacunacion.entities.MobilePhone;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Repository
public interface PhoneRepository extends JpaRepository<MobilePhone, Serializable> {
     Optional<MobilePhone> findByIdEmployeeAndNumberPhone(UUID employeeId, String numberPhone);
}
