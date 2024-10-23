package raiseteach.StudentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import raiseteach.StudentManagement.controller.converter.StudentConverter;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.domain.StudentDetail;
import raiseteach.StudentManagement.service.StudentService;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.data.StudentCourse;


import java.util.List;



@Controller
public class StudentController {

    private final StudentService service;
    private final StudentConverter converter;

    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/studentList")
    public String getStudentList(Model model) {
        List<StudentFolder> studentFolders = service.searchStudentFolderList();
        List<StudentCourse> studentCourses = service.searchStudentCourseList();

        // データの確認用ログ出力
        System.out.println("Student Folders: " + studentFolders);
        System.out.println("Student Courses: " + studentCourses);

        // 空データの場合はエラーメッセージを表示
        if (studentFolders.isEmpty()) {
            System.out.println("StudentFolderのデータが空です。");
        }


        model.addAttribute("studentList", converter.convertStudentDetails(studentFolders, studentCourses));
        return "studentList";
    }




    @GetMapping("/StudentCourseList")
    public String getStudentCourseList(Model model) {
        List<StudentCourse> courses = service.searchStudentCourseList();
        model.addAttribute("courseList", courses);
        return "studentCourseList";  // studentCourseList.html を表示
    }


    @GetMapping("/newStudent")
    public String newStudent(Model model) {
        model.addAttribute("studentDetail", new StudentDetail());
        return "registerStudent";
    }

    @PostMapping("/registerStudent")
    public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
        if (result.hasErrors()) {
            return "registerStudent";
        }
        System.out.println(studentDetail.getStudentFolder().getName() + "さんが新規受講生として登録されました。");


        return "redirect:/studentList";
    }
}
