package raiseteach.StudentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.service.StudentService;

import java.util.List;

@RestController

public class StudentController {

    private StudentService Service;

    @Autowired
    public StudentController(StudentService service)
    {
        Service = service;
    }

    @GetMapping("/studentCourseList")
    public List<StudentCourse> getStudentCourseList()
    {
        return Service.searchStudentCourseList();
    }

    @GetMapping("/studentFolderList")
    public List<StudentFolder> getStudentFolderList()
    {
        return Service.searchStudentFolderList();
    }
}
