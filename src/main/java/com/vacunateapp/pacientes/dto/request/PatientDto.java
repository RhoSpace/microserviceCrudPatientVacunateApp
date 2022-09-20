package com.vacunateapp.pacientes.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDto implements Serializable{

	@JsonProperty(value = "name", index = 1)
	private String name;
	
	@JsonProperty(value = "rut", index = 2)
	private String rut;
	
	@JsonProperty(value = "phone", index = 3)
	private String phone;
	
	@JsonProperty(value = "email", index = 4)
	private String email;
	
	@JsonProperty(value = "vaccinated", index = 5)
	private boolean vaccinated;
}
