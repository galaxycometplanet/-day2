package raiseteach.StudentManagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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
/**
 * 受講生の検索や登録、更新などを行うREST APIとして実行されるControllerです。
 */
@Validated
@RestController
public class StudentController {

    private final StudentService service;


    @Autowired
    public StudentController(StudentService service) {
        this.service = service;

    }

    /**
     * 受講生一覧検索です。（誰にもけて書いているか）
     * 全体検索を行うので、要件指定は行わないものとします。
     *
     * @return受講生一覧、全件数
     */

    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {


        return service.searchStudentFolderList();
    }

    /**
     * 受講生検索です。
     * IDに紐づく任意の受講生の情報を取得します。
     * @param id 受講生ID
     * @param model 受講生
     * @return
     */


    @GetMapping("/StudentFolder/{id}")
    public StudentDetail getStudentFolder(
            @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String id, Model model) {

        return service.searchStudent(id);
    }

    /**
     *
     * @param model
     * @return
     */

    @GetMapping("/newStudent")
    public String newStudent(Model model) {
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudentCourse(Arrays.asList(new StudentCourse()));
        model.addAttribute("studentDetail",studentDetail);
        return "registerStudent";
    }

    /**
     * 受講生詳細の更新を行います。キャンセルフラグの更新もここで行います。
     * @param studentDetail 受講生詳細
     * @return 実行結果
     */
    @PostMapping("/registerStudent")
    public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
        System.out.println(studentDetail.getStudentFolder().getName() + "さんが新規受講生として登録されました。");

       StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
        return ResponseEntity.ok(responseStudentDetail);
    }



    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail, BindingResult result) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
    }
}

