package com.socialMedia.socialMedia;

import com.socialMedia.socialMedia.Models.SocialUser;
import com.socialMedia.socialMedia.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocialMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApplication.class, args);
	}



}
