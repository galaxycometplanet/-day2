package raiseteach.StudentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raiseteach.StudentManagement.controller.converter.StudentConverter;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.domain.StudentDetail;
import raiseteach.StudentManagement.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class StudentController {

    private StudentService Service;
    private StudentConverter converter;

    @Autowired
    public StudentController(StudentService service,StudentConverter converter) {
        this.Service = service;
        this.converter = converter;
    }

    @GetMapping("/studentCourseList")
    public List<StudentDetail> getStudentList() {



        List<StudentCourse> studentCourses = Service.searchStudentCourseList();
        List<StudentFolder> studentFolders = Service.searchStudentFolderList();


        {
            return converter.convertStudentDetails(studentFolders, studentCourses);
        }
    }



    }




