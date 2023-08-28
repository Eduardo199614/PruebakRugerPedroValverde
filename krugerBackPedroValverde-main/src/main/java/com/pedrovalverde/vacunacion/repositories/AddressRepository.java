package com.pedrovalverde.vacunacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrovalverde.vacunacion.entities.Address;

import java.io.Serializable;

@Repository
public interface AddressRepository extends JpaRepository<Address, Serializable> {

}