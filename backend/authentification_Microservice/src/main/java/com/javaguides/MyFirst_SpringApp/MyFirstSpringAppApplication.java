package com.javaguides.MyFirst_SpringApp;

import com.javaguides.MyFirst_SpringApp.sec.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
@EntityScan(basePackages = {"com.javaguides.MyFirst_SpringApp.sec.entities"})
public class MyFirstSpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstSpringAppApplication.class, args);

	}

	}


