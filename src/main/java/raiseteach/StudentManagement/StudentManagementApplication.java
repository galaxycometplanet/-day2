package raiseteach.StudentManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import raiseteach.StudentManagement.data.StudentCourse;

import java.util.List;

@SpringBootApplication

public class StudentManagementApplication {


	private StudentRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}


}



