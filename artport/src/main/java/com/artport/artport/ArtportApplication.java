package com.artport.artport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ArtportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtportApplication.class, args);
	}
}
