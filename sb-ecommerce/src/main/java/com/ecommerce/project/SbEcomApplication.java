package com.ecommerce.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ssl.SslAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;

@SpringBootApplication(exclude = {
		SslAutoConfiguration.class,
		RestClientAutoConfiguration.class       // Exclude Actuator health endpoint auto-config
})
public class SbEcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbEcomApplication.class, args);
	}
}
