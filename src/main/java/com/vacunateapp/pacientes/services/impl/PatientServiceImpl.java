package com.vacunateapp.pacientes.services.impl;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.vacunateapp.pacientes.dto.request.PatientDto;
import com.vacunateapp.pacientes.dto.response.PatientResponseDto;
import com.vacunateapp.pacientes.entities.Patient;
import com.vacunateapp.pacientes.exceptions.AppInternalServerErrorException;
import com.vacunateapp.pacientes.exceptions.AppNotFoundException;
import com.vacunateapp.pacientes.repositories.PatientRepository;
import com.vacunateapp.pacientes.services.PatientService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;

	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public Patient findById(Long id) throws AppNotFoundException {
		log.info("findById: {}", id);
		return patientRepository.findByIdOptional(id)
				.orElseThrow(() -> new AppNotFoundException("No Encontre el paciente con id: " + id));
	}

	@Override
	public ResponseEntity<?> savePatient(PatientDto dto) throws AppInternalServerErrorException, ConstraintViolationException {
		try {
			Patient newPatient = new Patient();
			newPatient.setId(null);
			newPatient.setName(dto.getName());
			newPatient.setEmail(dto.getEmail());
			newPatient.setRut(dto.getRut());
			newPatient.setPhone(dto.getPhone());
			newPatient.setVaccinated(dto.isVaccinated());

			// pacienteRepository.findByRut(dto.getRut());

			Patient patient = patientRepository.save(newPatient);

			// Parseamos al DTO de salida
			PatientResponseDto dtoPatient = new PatientResponseDto();

			dtoPatient.setName(patient.getName());
			dtoPatient.setEmail(patient.getEmail());
			dtoPatient.setRut(patient.getRut());
			dtoPatient.setPhone(patient.getPhone());
			dtoPatient.setVaccinated(patient.isVaccinated());

			return ResponseEntity.status(HttpStatus.OK).body(dtoPatient);

		}catch (Exception e) {
			e.printStackTrace();
			throw new AppInternalServerErrorException("Error al crear paciente");
		}
	}

	@Override
	public ResponseEntity<?> findAllPatient() throws AppInternalServerErrorException {
		try {
			// Busco en BD
			List<Patient> patientBD = patientRepository.findAll();

			return ResponseEntity.status(HttpStatus.OK).body(patientBD);

		} catch (Exception e) {
			e.printStackTrace();
			throw new AppInternalServerErrorException("Error al buscar todos los Pacientes");
		}
	}

	@Override
	public ResponseEntity<?> findByIdPatient(Long id) throws AppNotFoundException {

		Patient patientBd = findById(id);

		return ResponseEntity.status(HttpStatus.OK).body(patientBd);
	}

	@Override
	public ResponseEntity<?> updatePatient(Long id, PatientDto dto)
			throws AppNotFoundException, AppInternalServerErrorException {
		try {
			findById(id);

			Patient newPatient = new Patient();
			newPatient.setId(id);
			newPatient.setName(dto.getName());
			newPatient.setEmail(dto.getEmail());
			newPatient.setRut(dto.getRut());
			newPatient.setPhone(dto.getPhone());
			newPatient.setVaccinated(dto.isVaccinated());

			// Actualizo en BD
			Patient patient = patientRepository.save(newPatient);

			return ResponseEntity.status(HttpStatus.OK).body(patient);
		} catch (AppNotFoundException e) {
			e.printStackTrace();
			throw new AppNotFoundException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppInternalServerErrorException("Error al actualizar el paciente");
		}
	}

	@Override
	public ResponseEntity<?> deletePatient(Long id) throws AppNotFoundException, AppInternalServerErrorException {
		try {
			findById(id);
			patientRepository.deleteById(id);

			return ResponseEntity.status(HttpStatus.OK).body("Borrado!");
		} catch (AppNotFoundException e) {
			e.printStackTrace();
			throw new AppNotFoundException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppInternalServerErrorException("Error al borrar el paciente");
		}
	}

	@Override
	public Patient findByRut(String rut) throws AppNotFoundException {
		log.info("Buscando el rut: {}", rut);
		return patientRepository.findByRut(rut)
				.orElseThrow(() -> new AppNotFoundException("No se encontro el paciente con el rut: " + rut));
	}

}
