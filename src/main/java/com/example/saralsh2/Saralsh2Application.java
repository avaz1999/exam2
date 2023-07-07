package com.example.saralsh2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.saralsh2.repository.CalculationTypeRepository.java")
public class Saralsh2Application {

    public static void main(String[] args) {
        SpringApplication.run(Saralsh2Application.class, args);
    }

}
