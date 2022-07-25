package com.lib.api;

import com.lib.api.entities.Rol;
import com.lib.api.repositories.RolRepository;
import com.lib.api.services.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

@SpringBootApplication
public class ApiLibreriaApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	public static void main(String[] args) {SpringApplication.run(ApiLibreriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		String password = "12345";
//
//		for(int i=0; i<4; i++){
//			String passwordBcrypt = passwordEncode.encode(password);
//			System.out.println(passwordBcrypt);
//		}
	}
}
