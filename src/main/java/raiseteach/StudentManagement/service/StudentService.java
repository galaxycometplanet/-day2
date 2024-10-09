package raiseteach.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raiseteach.StudentManagement.StudentRepository;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.domain.StudentDetail;


@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<StudentCourse> searchStudentCourseList() {
        // コース情報のみ絞り込み
        return repository.searchStudentCourse();
    }


    public List<StudentFolder> searchStudentFolderList() {
        // 年齢30以上の学生フォルダーを抽出
        return repository.search()
                .stream()
                .filter(folder -> folder.getAge() >= 30)  // age が30以上のものをフィルタリング
                .collect(Collectors.toList());
    }

    public List<Integer> extractAgesFromStudentFolderList() {
        // 全体の抽出から年齢のみをリストに入れる
        return repository.search()
                .stream()
                .map(StudentFolder::getAge)  // StudentFolder から age を抽出
                .collect(Collectors.toList());
    }


    @Transactional
    public void registerStudent(StudentDetail studentDetail) {
        repository.registerStudent(studentDetail.getStudentFolder());
    }
}