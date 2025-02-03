package com.melodyguard.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@ComponentScan(basePackages = "com.melodyguard.api")
@EnableMongoAuditing
public class MelodyGuardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MelodyGuardApplication.class, args);
	}

}
