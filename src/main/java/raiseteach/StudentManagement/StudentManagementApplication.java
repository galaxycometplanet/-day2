package raiseteach.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import raiseteach.StudentManagement.repository.StudentRepository;

@SpringBootApplication

public class StudentManagementApplication {


	private StudentRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}


}



