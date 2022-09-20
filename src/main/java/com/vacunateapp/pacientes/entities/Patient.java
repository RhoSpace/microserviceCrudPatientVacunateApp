package com.vacunateapp.pacientes.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
public class Patient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", length = 256, nullable = false)
	private String name;
	@Column(name = "RUT", length = 15, nullable = false, unique = true)
	private String rut;
	@Column(name = "PHONE", length = 12, nullable = false)
	private String phone;
	@Column(name = "EMAIL", length = 100, nullable = false)
	private String email;
	@Column(name = "VACCINATED")
	private boolean vaccinated;
}