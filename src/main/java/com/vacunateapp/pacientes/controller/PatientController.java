package com.vacunateapp.pacientes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vacunateapp.pacientes.dto.request.PatientDto;
import com.vacunateapp.pacientes.entities.Patient;
import com.vacunateapp.pacientes.exceptions.AppInternalServerErrorException;
import com.vacunateapp.pacientes.exceptions.AppNotFoundException;
import com.vacunateapp.pacientes.services.PatientService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("api/patient")
@CrossOrigin("*")
@Log4j2
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    
    @GetMapping
    public ResponseEntity<?> findAllPatient() throws AppInternalServerErrorException{
        return patientService.findAllPatient();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPatient(@PathVariable Long id) throws AppNotFoundException{
        return patientService.findByIdPatient(id);
    }

    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody PatientDto dto, BindingResult result) throws AppInternalServerErrorException{
        if(result.hasErrors()) {
        	return hasError(result);
        }
        return patientService.savePatient(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody PatientDto dto)throws AppInternalServerErrorException, AppNotFoundException {
        return patientService.updatePatient(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) throws AppInternalServerErrorException, AppNotFoundException{
        return patientService.deletePatient(id);
    }
    

    @GetMapping("/rut/{rut}")
    public Patient findByRutPatient(@PathVariable String rut) throws AppNotFoundException {
        return patientService.findByRut(rut);
    }
    
    private ResponseEntity<?> hasError(BindingResult result){
        log.info("Ha Ocurrido un error en uno de los campos del Request");
        Map<String, Object> response = new HashMap<>();

        List<String> errors = result.getFieldErrors()
                .stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());

        response.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
