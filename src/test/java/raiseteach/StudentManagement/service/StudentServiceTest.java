package raiseteach.StudentManagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raiseteach.StudentManagement.controller.converter.StudentConverter;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.domain.StudentDetail;
import raiseteach.StudentManagement.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentConverter converter;

    private StudentService sut;

    @BeforeEach
    void before() {
        sut = new StudentService(repository, converter);
    }

    @Test
    void 受講生詳細の一覧検索_リポジトリとコンバーターの処理が適切に呼び出せていること() {
        //事前準備
        StudentService sut = new StudentService(repository, converter);
        List<StudentFolder> studentFolderList = new ArrayList<>();
        List<StudentCourse> studentCourseList = new ArrayList<>();

        when(repository.search()).thenReturn(studentFolderList);
        when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

        //実行
        sut.searchStudentFolderList();

        //後続処理
        Mockito.verify(repository, times(1)).search();
        Mockito.verify(repository, times(1)).searchStudentCourseList();
        Mockito.verify(converter, times(1)).convertStudentDetails(studentFolderList, studentCourseList);

    }

    @Test
    void 受講生詳細の検索_リポジトリの処理が適切に呼び出せていること() {
        //事前準備
        String id = "9999";
        StudentFolder studentFolder = new StudentFolder();
        studentFolder.setId(id);
        when(repository.searchStudent(id)).thenReturn(studentFolder);
        List<StudentFolder> studentFolderList = new ArrayList<>();
        List<StudentCourse> studentCourseList = new ArrayList<>();

        when(repository.searchStudent(id)).thenReturn(studentFolder);
        when(repository.searchStudentCourse(id)).thenReturn(new ArrayList<>());

        StudentDetail expected = new StudentDetail(studentFolder, new ArrayList<>());
        //実行
        StudentDetail actual = sut.searchStudent(id);

        //後続処理
        Mockito.verify(repository, times(1)).searchStudent(id);
        Mockito.verify(repository, times(1)).searchStudentCourse(id);
        Assertions.assertEquals(expected.getStudentFolder().getId(), actual.getStudentFolder().getId());

    }

    @Test
    void 受講生詳細の登録_リポジトリの処理が適切に呼び出せていること() {
        //事前準備
        StudentFolder studentFolder = new StudentFolder();
        StudentCourse studentCourse = new StudentCourse();
        List<StudentCourse> studentCourseList = new ArrayList<>();
        StudentDetail studentDetail = new StudentDetail(studentFolder, studentCourseList);

        //実行
        sut.registerStudent(studentDetail);

        //後続処理
        Assertions.assertEquals("999", studentCourse.getDatamineID());
        Assertions.assertEquals(LocalDateTime.now().getHour(),
                studentCourse.getStart().getHour());
        Assertions.assertEquals(LocalDateTime.now().plusYears(1).getYear(),
                studentCourse.getEnd().getHour());

    }


    @Test
    void 受講生詳細の更新_リポジトリの処理が適切に呼び出せていること() {
        //事前準備
        StudentFolder studentFolder = new StudentFolder();
        StudentCourse studentCourse = new StudentCourse();
        List<StudentCourse> studentCourseList = List.of(studentCourse);
        StudentDetail studentDetail = new StudentDetail(studentFolder, studentCourseList);


        //実行
        sut.registerStudent(studentDetail);

        //後続処理
        Mockito.verify(repository, times(1)).updateStudent(studentFolder);
        Mockito.verify(repository, times(1)).updateStudentsCourses(studentCourse);


    }
}