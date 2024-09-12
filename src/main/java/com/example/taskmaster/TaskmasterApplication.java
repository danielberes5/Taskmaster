package com.example.taskmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.taskmaster.models")
@ComponentScan(basePackages = "com.example.taskmaster.config")
public class TaskmasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmasterApplication.class, args);
	}

}
