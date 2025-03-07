package com.example.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		//view in web http://localhost:8080/hello
		SpringApplication.run(Application.class, args);
	}

}
