package io.benfill.isdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@ComponentScan(basePackages = "io.benfill.isdb")
@EnableMongoAuditing
public class IsdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsdbApplication.class, args);
	}

}
