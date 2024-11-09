package raiseteach.StudentManagement;

import org.apache.ibatis.annotations.*;

import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM student_course")
    List<StudentCourse> searchStudentCourseList();

    @Select("SELECT * FROM student_folder")
    List<StudentFolder> search();

    @Select("SELECT * FROM student_folder WHERE id =#{id}")
    StudentFolder searchStudent(String id);

    @Select("SELECT * FROM student_course WHERE datamineID =#{datamineID}")
    List<StudentCourse> searchStudentCourse(String datamineID);


    @Select("SELECT * FROM student_folder where age > 30")
    List<StudentFolder> searchover();

    @Insert(
            "INSERT INTO student_folder(name,namemini,penname,maleaddress,address,age,gender,remark,isDeleted)"
             +" VALUES(#{name},#{namemini}, #{penname},#{maleaddress},#{address},#{age}, #{gender},#{remark} ,false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(StudentFolder student);

    @Insert("INSERT INTO student_course(datamineID,coursename,start,end)"
    + "VALUES(#{datamineID}, #{coursename}, #{start}, #{end})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudentsCourses(StudentCourse studentCourse);

    @Update(
            "UPDATE student_folder SET name = #{name},namemini = #{namemini},penname = #{penname},maleaddress = #{maleaddress},address = #{address},"
                    + "age = #{age},gender =  #{gender},remark = #{remark} ,isDeleted = #{isDeleted} WHERE id = #{id}")
    void updateStudent(StudentFolder student);

    @Update("UPDATE student_course SET coursename = #{coursename} WHERE id=#{id})"
            + "VALUES(#{datamineID}, #{coursename}, #{start}, #{end})")

    void updateStudentsCourses(StudentCourse studentCourse);



}
