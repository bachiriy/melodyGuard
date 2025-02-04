package com.melodyguard.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = "com.melodyguard.api")
@EnableMongoAuditing
public class MelodyGuardApplication {
	public static void main(String[] args) {
		SpringApplication.run(MelodyGuardApplication.class, args);
	}
}
