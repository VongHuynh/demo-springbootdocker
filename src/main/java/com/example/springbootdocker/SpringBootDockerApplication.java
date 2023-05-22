package com.example.springbootdocker;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SpringBootDockerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDockerApplication.class, args);
    }

}
