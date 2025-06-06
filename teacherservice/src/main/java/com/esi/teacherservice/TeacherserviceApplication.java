package com.esi.teacherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeacherserviceApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("teacherservice_db");
		SpringApplication.run(TeacherserviceApplication.class, args);
	}
}
