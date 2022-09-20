package com.vacunateapp.pacientes.services;

import org.springframework.http.ResponseEntity;

import com.vacunateapp.pacientes.dto.request.PatientDto;
import com.vacunateapp.pacientes.entities.Patient;
import com.vacunateapp.pacientes.exceptions.AppInternalServerErrorException;
import com.vacunateapp.pacientes.exceptions.AppNotFoundException;


public interface PatientService {


    public ResponseEntity<?> savePatient(PatientDto dto)throws AppInternalServerErrorException;

    public ResponseEntity<?> findAllPatient() throws AppInternalServerErrorException;

    public ResponseEntity<?> findByIdPatient(Long id) throws AppNotFoundException;

    public ResponseEntity<?> updatePatient(Long id, PatientDto dto) throws AppNotFoundException, AppInternalServerErrorException;

    public ResponseEntity<?> deletePatient(Long id) throws AppNotFoundException, AppInternalServerErrorException;

    public Patient findById(Long id) throws AppNotFoundException;

    public Patient findByRut(String rut) throws AppNotFoundException;
}
