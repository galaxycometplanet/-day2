package raiseteach.StudentManagement.service;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raiseteach.StudentManagement.repository.StudentRepository;
import raiseteach.StudentManagement.controller.converter.StudentConverter;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import raiseteach.StudentManagement.domain.StudentDetail;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や登録・更新処理を行います。
 */

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private StudentRepository repository;
    private StudentConverter converter;

    @Autowired
    public StudentService(StudentRepository repository, StudentConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }




    /**
     * 受講生詳細検索です。
     * IDに紐づく任意の受講生の情報を取得します。
     * @param id 受講生ID
     * @return 受講生
     */


    public StudentDetail searchStudent(String id) {
       StudentFolder studentFolder =  repository.searchStudent(id);
       List<StudentCourse> StudentCourse = repository.searchStudentCourse(studentFolder.getId());


       return new StudentDetail(studentFolder, StudentCourse);
    };

    /**
     * 受講生詳細一覧検索です。
     * 全権検索を行うので、じょうほう検索は行いません
     *
     * 受講生詳細一覧（全権）
     * @return
     */

    public List<StudentDetail> searchStudentFolderList() {
        List<StudentFolder> studentlist = repository.search();
        List<StudentCourse> studentCourses = repository.searchStudentCourseList();
        repository.searchStudentCourseList();
        return converter.convertStudentDetails(studentlist, studentCourses);
    }


    public List<Integer> extractAgesFromStudentFolderList() {
        // 全体の抽出から年齢のみをリストに入れる
        return repository.search()
                .stream()
                .map(StudentFolder::getAge)  // StudentFolder から age を抽出
                .collect(Collectors.toList());
    }

    /**
     * 受講生詳細の登録を行います。受講生と受講生コース情報を個別に入力し、受講生コース情報には受講生情報を紐づける値とコースを選定します。
     *
     * @param studentDetail　受講生詳細
     * @return 登録情報を付与した受講生詳細
     */

    @Transactional
    public StudentDetail registerStudent(StudentDetail studentDetail) {
        StudentFolder studentFolder = studentDetail.getStudentFolder();
        repository.registerStudent(studentFolder);
        //TODO:コース情報登録も行う
        for(StudentCourse studentCourses:studentDetail.getStudentCourse())
        {
            initStudentCourse(studentCourses, studentFolder);

            repository.registerStudentsCourses(studentCourses);
        }
        return studentDetail;
    }

    /**
     * 受講生コース情報を登録する際の初期設定を登録する。
     * @param studentCourses 受講生コース情報
     * @param studentFolder 受講生
     */
    private static void initStudentCourse(StudentCourse studentCourses, StudentFolder studentFolder) {
        LocalDateTime now = LocalDateTime.now();
        studentCourses.setDatamineID(studentFolder.getId());

        studentCourses.setStart(now);
        studentCourses.setEnd(now.plusYears(1));
    }

    /**
     * 受講生詳細の更新を行います。
     * 受講生の情報と受講生コースそれぞれ更新します。
     * @param studentDetail 受講生詳細
     */
    @Transactional
    public void updateStudent(StudentDetail studentDetail) {
        repository.updateStudent(studentDetail.getStudentFolder());
        //TODO:コース情報登録も行う
        studentDetail.getStudentCourse().forEach(studentCourses -> {
            studentCourses.setDatamineID(studentDetail.getStudentFolder().getId());
            repository.updateStudentsCourses(studentCourses);
        });
    }
}