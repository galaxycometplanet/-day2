package raiseteach.StudentManagement.controller.converter;



import org.springframework.stereotype.Component;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.domain.StudentDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 受講生情報と受講生コース情報を受講生詳細にコンバーターです。
 */

@Component
public class StudentConverter {

    /**
     * 受講生に紐づく受講生コース情報をマッピングする。
     * 受講生コース情報は受講生に対して複数存在するのでループを回して受講生詳細情報を組み立てる。
     *
     * @param studentFolders　受講生一覧
     * @param studentCourses 受講生コース情報のリスト
     * @return 受講生詳細情報のリスト
     */

    public List<StudentDetail> convertStudentDetails(List<StudentFolder> studentFolders, List<StudentCourse> studentCourses) {
        List<StudentDetail> studentDetails = new ArrayList<>();

        // 各StudentFolderに対して、StudentDetailを作成
        for (StudentFolder studentFolder : studentFolders) {
            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setStudentFolder(studentFolder);

            // 同じIDを持つStudentCourseをフィルタリング
            List<StudentCourse> convertStudentCourse = studentCourses.stream()
                    .filter(studentCourse -> studentFolder.getId().equals(studentCourse.getId()))
                    .collect(Collectors.toList());

            studentDetail.setStudentCourseList(convertStudentCourse);
            studentDetails.add(studentDetail);
        }
        return studentDetails;
    }
}
