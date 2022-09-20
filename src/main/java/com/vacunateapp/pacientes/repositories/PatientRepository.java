package com.vacunateapp.pacientes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vacunateapp.pacientes.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

    @Query("SELECT e FROM Patient e WHERE e.id = ?1")
    Optional<Patient> findByIdOptional(Long id);
	
    @Query("SELECT e FROM Patient e WHERE e.rut = ?1")
    Optional<Patient> findByRut(String rut);

}
