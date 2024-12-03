package raiseteach.StudentManagement.controller;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.service.StudentService;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import jakarta.validation.Validation;

import java.util.Set;


@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StudentService service;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void 受講生詳細の一覧検索が実行できて空のリストが返ってくること()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/studentList"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"student\":null,\"studentCourseList\":null}]"));

        verify(service, times(1)).searchStudentFolderList();
    }

    @Test
    void 受講生詳細の受講生でIDに数字以外を用いた時にチェックに掛かること(){
        StudentFolder student = new StudentFolder();
        student.setId("テストです");
        student.setName("FukuzawaYukichi");
        student.setNamemini("Fukuzawa");
        student.setPenname("1mannyenn");
        student.setMaleaddress("meiji@gakumon.jp");
        student.setAddress("Fukuokakenn");
        student.setGender("man");

        Set<ConstraintViolation<StudentFolder>> violations = validator.validate(student);

        assertThat(violations.size()).isEqualTo(0);
        assertThat(violations).extracting("message").containsOnly("数字のみ入力するようにしてください");

    }
}