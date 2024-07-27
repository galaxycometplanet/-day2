package raiseteach.StudentManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	@Autowired
	private StudentRepository repository;

	private String name = "Enami Koji";
	private String age = "37";



	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@GetMapping("/name")
	public String getName(@RequestParam String name) {
		Student student = repository.searchByName(name);
		return student.getName()+" "+student.getAge()+"歳";
	}

	@PostMapping("/name")
	public void setName(String name, int age) {
		repository.registerStudent(name, age);
	}

	@PostMapping("/updatename")
	public void upName(String name) {
		this.name = name;
	}
}

