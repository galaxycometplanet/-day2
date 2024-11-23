package raiseteach.StudentManagement.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;

import java.util.List;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoriです。
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
     * 受講生の検索を行います。
     *
     * @param id 受講生ID
     * @return 受講生
     */
    StudentFolder searchStudent(String id);

    /**
     * 全件検索を行います。
     *
     * @return 受講生一覧(全権)
     */

    List<StudentCourse> searchStudentCourseList();

    /**
     * 受講生IDに紐づく受講生コース情報を検索します。
     *
     * @param datamineID 受講生Id
     * @return 受講生ID
     */

    List<StudentCourse> searchStudentCourse(String datamineID);

    /**
     * 受講生を新規登録します。
     * IDに関しては自動裁判を行う。
     *
     * @param student 受講生
     */

    void registerStudent(StudentFolder student);

    /**
     * 受講生コース情報を新規登録します。
     * IDに関しては自動裁判を行う。
     *
     * @param studentCourse 受講生コース情報
     */

    void registerStudentsCourses(StudentCourse studentCourse);

    /**
     * 受講生を更新します。
     *
     * @param student 受講生
     */

    void updateStudent(StudentFolder student);

    /**
     * 受講生コースのコース名を更新します。
     *
     * @param studentCourse 受講生コース
     */

    void updateStudentsCourses(StudentCourse studentCourse);
}
