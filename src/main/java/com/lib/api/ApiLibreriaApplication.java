package com.lib.api;

import com.lib.api.entities.Rol;
import com.lib.api.repositories.RolRepository;
import com.lib.api.services.RolServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;

@SpringBootApplication
public class ApiLibreriaApplication {

	public static void main(String[] args) {SpringApplication.run(ApiLibreriaApplication.class, args);
	}

}
