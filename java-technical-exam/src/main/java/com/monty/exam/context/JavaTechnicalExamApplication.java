package com.monty.exam.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(value = "com.monty.exam")
@EntityScan(value = "com.monty.exam") 
@EnableJpaRepositories(basePackages = "com.monty.exam.repository")
public class JavaTechnicalExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaTechnicalExamApplication.class, args);
	}

}
