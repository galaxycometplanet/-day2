package raiseteach.StudentManagement;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM student_course")
    List<Student> search();



}
