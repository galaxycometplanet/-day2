package raiseteach.StudentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import raiseteach.StudentManagement.controller.converter.StudentConverter;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.domain.StudentDetail;
import raiseteach.StudentManagement.service.StudentService;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.data.StudentCourse;


import java.util.Arrays;
import java.util.List;



@RestController
public class StudentController
{

    private final StudentService service;
    private final StudentConverter converter;

    @Autowired
    public StudentController(StudentService service, StudentConverter converter)
    {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList()
    {
        List<StudentFolder> studentFolders = service.searchStudentFolderList();
        List<StudentCourse> studentCourses = service.searchStudentCourseList();

        return converter.convertStudentDetails(studentFolders, studentCourses);
    }





    @GetMapping("/newStudent")
    public String newStudent(Model model)
    {
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudentCourse(Arrays.asList(new StudentCourse()));
        model.addAttribute("studentDetail",studentDetail);
        return "registerStudent";
    }

    @PostMapping("/registerStudent")
    public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "registerStudent";
        }
        System.out.println(studentDetail.getStudentFolder().getName() + "さんが新規受講生として登録されました。");

        service.registerStudent(studentDetail);
        return "redirect:/studentList";
    }



    @PostMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail, BindingResult result)
    {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
    }
}

