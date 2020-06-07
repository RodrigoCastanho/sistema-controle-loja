package br.com.devrdgao.controleloja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ControlelojaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlelojaApplication.class, args);
		System.out.println("-- SISTEMA INICIADO --");	
	}

}
