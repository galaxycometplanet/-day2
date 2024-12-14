package raiseteach.StudentManagement.Converter;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import raiseteach.StudentManagement.controller.converter.StudentConverter;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.domain.StudentDetail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

public class StudentConverterTest {

    private StudentConverter sut;

    @BeforeEach
    void before(){
        sut = new StudentConverter();
    }

    @Test
    void 受講生のリストと受講生コース情報のリストを渡して受講生詳細のリストが作成できること(){
        StudentFolder student = new StudentFolder();
        student.setId("テストです");
        student.setName("FukuzawaYukichi");
        student.setNamemini("Fukuzawa");
        student.setPenname("1mannyenn");
        student.setMaleaddress("meiji@gakumon.jp");
        student.setAddress("Fukuokakenn");
        student.setAge(36);
        student.setGender("男性");
        student.setRemark("");

        StudentCourse studentcourse = new StudentCourse();
        studentcourse.setId("2");
        studentcourse.setDatamineID("2");
        studentcourse.getCoursename("Physics");
        studentcourse.getStart(LocalDateTime.now());
        studentcourse.getEnd(LocalDateTime.now().plusYears(1));

        List<StudentFolder> studentFolderList = List.of(student);
        List<StudentCourse> studentCourseList = List.of(studentcourse);

        List<StudentDetail> actual = sut.convertStudentDetails(studentFolderList, studentCourseList);

        assertThat(actual.get(0).getStudentFolder()).isEqualTo(student);
        assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);
    }
    @Test
    void 受講生のリストと受講生コース情報のリストを渡したときに受講生IDが紐づかない受講生コース情報は除外されること() {
        StudentFolder student = new StudentFolder();
        student.setId("テストです");
        student.setName("FukuzawaYukichi");
        student.setNamemini("Fukuzawa");
        student.setPenname("1mannyenn");
        student.setMaleaddress("meiji@gakumon.jp");
        student.setAddress("Fukuokakenn");
        student.setAge(36);
        student.setGender("男性");
        student.setRemark("");

        StudentCourse studentcourse = new StudentCourse();
        studentcourse.setId("2");
        studentcourse.setDatamineID("2");
        studentcourse.getCoursename("Physics");
        studentcourse.getStart(LocalDateTime.now());
        studentcourse.getEnd(LocalDateTime.now().plusYears(1));

        List<StudentFolder> studentList = List.of(student);
        List<StudentCourse> studentCourseList = List.of(studentcourse);

        List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

        assertThat(actual.get(0).getStudentFolder()).isEqualTo(student);
        assertThat(actual.get(0).getStudentCourseList()).isEmpty();
    }


}
