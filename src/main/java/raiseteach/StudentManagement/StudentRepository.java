package raiseteach.StudentManagement;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM student_course")
    List<StudentCourse> searchStudentCourse();

    @Select("SELECT * FROM student_folder")
    List<StudentFolder> search();

    @Select("SELECT * FROM student_folder where age > 30")
    List<StudentFolder> searchover();

    @Insert("INSERT INTO students VALUES(#{name},#{namemini}, #{penname},#{maleaddress},#{address},#{age}, #{gender},#{remark} ,false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(StudentFolder student);



}
