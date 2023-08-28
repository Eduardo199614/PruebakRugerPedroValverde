package com.pedrovalverde.vacunacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrovalverde.vacunacion.entities.VaccinationType;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VaccineTypeRepository extends JpaRepository<VaccinationType, Serializable> {
    Optional<VaccinationType> findById(UUID id);
}