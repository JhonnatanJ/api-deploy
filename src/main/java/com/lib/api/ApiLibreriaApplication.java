package com.lib.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiLibreriaApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	public static void main(String[] args) {SpringApplication.run(ApiLibreriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		String password = "vendedorI";
//		String passwordBcrypt = passwordEncode.encode(password);
//		System.out.println(passwordBcrypt);
//		String password2 = "adminJ";
//		String passwordBcrypt2 = passwordEncode.encode(password2);
//		System.out.println(passwordBcrypt2);
	}
}
