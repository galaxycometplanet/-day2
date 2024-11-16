package raiseteach.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raiseteach.StudentManagement.StudentRepository;
import raiseteach.StudentManagement.controller.converter.StudentConverter;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import raiseteach.StudentManagement.data.StudentFolder;
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
     * 受講生検索です。
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
     * 受講生一覧検索です。
     * 全権検索を行うので、じょうほう検索は行いません
     *
     * 受講生一覧（全権）
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


    @Transactional
    public StudentDetail registerStudent(StudentDetail studentDetail)
    {
        repository.registerStudent(studentDetail.getStudentFolder());
        //TODO:コース情報登録も行う
        for(StudentCourse studentCourses:studentDetail.getStudentCourse())
        {
            studentCourses.setDatamineID(studentDetail.getStudentFolder().getId());
            studentCourses.setStart(LocalDateTime.now());
            studentCourses.setEnd(LocalDateTime.now().plusYears(1));

            repository.registerStudentsCourses(studentCourses);
        }
        return studentDetail;
    }

    @Transactional
    public void updateStudent(StudentDetail studentDetail)
    {
        repository.updateStudent(studentDetail.getStudentFolder());
        //TODO:コース情報登録も行う
        for(StudentCourse studentCourses:studentDetail.getStudentCourse())
        {
            studentCourses.setDatamineID(studentDetail.getStudentFolder().getId());
            repository.updateStudentsCourses(studentCourses);
        }
    }
}