package raiseteach.StudentManagement;




import org.apache.ibatis.annotations.*;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;

import java.util.List;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepojitoriです。
 */

@Mapper
public interface StudentRepository {


    /**
     * 受講生の検索を行います。
     *
     * @return 受講生
     */

    List<StudentFolder> search();

    /**
     *受講生の検索を行います。
     *
     * @param id 受講生ID
     * @return 受講生
     */

    StudentFolder searchStudent(String id);    /**
     * 全件検索を行います。
     *
     * @return 受講生一覧(全権)
     */

    @Select("SELECT * FROM student_course")
    List<StudentCourse> searchStudentCourseList();

    /**受講生IDに紐づく受講生コース情報を検索します。
     *
     * @param datamineID 受講生Id
     * @return 受講生ID
     */

    @Select("SELECT * FROM student_course WHERE datamineID =#{datamineID}")
    List<StudentCourse> searchStudentCourse(String datamineID);



    @Select("SELECT * FROM student_folder where age > 30")
    List<StudentFolder> searchover();

    /**
     *受講生を新規登録します。
     *IDに関しては自動裁判を行う。
     * @param student 受講生
     */

    @Insert(
            "INSERT INTO student_folder(name,namemini,penname,maleaddress,address,age,gender,remark,isDeleted)"
             +" VALUES(#{name},#{namemini}, #{penname},#{maleaddress},#{address},#{age}, #{gender},#{remark} ,false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(StudentFolder student);

    /**
     *受講生コース情報を新規登録します。
     *IDに関しては自動裁判を行う。
     * @param studentCourse 受講生コース情報
     */

    @Insert("INSERT INTO student_course(datamineID,coursename,start,end)"
    + "VALUES(#{datamineID}, #{coursename}, #{start}, #{end})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudentsCourses(StudentCourse studentCourse);

    /**
     * 受講生を更新します。
     *
     * @param student 受講生
     */
    @Update(
            "UPDATE student_folder SET name = #{name},namemini = #{namemini},penname = #{penname},maleaddress = #{maleaddress},address = #{address},"
                    + "age = #{age},gender =  #{gender},remark = #{remark} ,isDeleted = #{isDeleted} WHERE id = #{id}")
    void updateStudent(StudentFolder student);

    /**
     * 受講生コースのコース名を更新します。
     * @param studentCourse 受講生コース
     */
    @Update("UPDATE student_course SET coursename = #{coursename} WHERE id=#{id}")

    void updateStudentsCourses(StudentCourse studentCourse);



}
