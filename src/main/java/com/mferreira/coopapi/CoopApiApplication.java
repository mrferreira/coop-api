package com.mferreira.coopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaRepositories
@EntityScan(basePackages = {"com.mferreira.coopapi.model"})
public class CoopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoopApiApplication.class, args);
	}

}
