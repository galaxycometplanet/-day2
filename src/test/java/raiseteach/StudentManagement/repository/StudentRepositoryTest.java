package raiseteach.StudentManagement.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raiseteach.StudentManagement.data.StudentFolder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@MybatisTest
class StudentRepositoryTest {

     @Autowired
     private StudentRepository sut;

     @Test
     void 受講生の全権検索が行えること() {
        List<StudentFolder> actual = sut.search();
         assertThat(actual.size()).isEqualTo(5);
      }

    @Test
    void 受講生の登録が完璧に行えること(){
        List<StudentFolder> actual = sut.search();
        assertThat(actual.size()).isEqualTo(5);
    }

    @Test
    void 受講生の登録が行えること(){
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

        sut.registerStudent(student);

        List<StudentFolder> actual = sut.search();

        assertThat(actual.size()).isEqualTo(6);
    }
}


