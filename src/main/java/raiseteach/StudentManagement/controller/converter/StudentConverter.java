package raiseteach.StudentManagement.controller.converter;

import org.springframework.stereotype.Component;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.data.StudentFolder;
import raiseteach.StudentManagement.domain.StudentDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter {



    public  List<StudentDetail> convertStudentDetails(List<StudentFolder> studentFolders, List<StudentCourse> studentCourses) {
        List<StudentDetail> studentDetails = new ArrayList<>();
        studentFolders.forEach(studentFolder -> {
            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setStudentFolder(studentFolders);

            List<StudentCourse> convertStudentCourse = studentCourses.stream()
                    .filter(studentCoursed -> studentFolder.getId().equals(studentCoursed.getId()))
                    .collect(Collectors.toList());

            studentDetail.setStudentCourse(convertStudentCourse);
            studentDetails.add(studentDetail);
        });
        return studentDetails;
    }
}
