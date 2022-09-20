package com.vacunateapp.pacientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MicroservicioPacientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioPacientesApplication.class, args);
	}

}
