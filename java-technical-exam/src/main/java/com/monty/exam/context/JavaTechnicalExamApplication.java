package com.monty.exam.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.monty.exam")
public class JavaTechnicalExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaTechnicalExamApplication.class, args);
	}

}
