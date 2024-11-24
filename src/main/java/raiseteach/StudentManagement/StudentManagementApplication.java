package raiseteach.StudentManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import raiseteach.StudentManagement.repository.StudentRepository;

@OpenAPIDefinition(info = @Info(title = "受講生管理システム"))
@SpringBootApplication

public class StudentManagementApplication {


	private StudentRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}


}



