package raiseteach.StudentManagement.domain;


import lombok.Getter;
import lombok.Setter;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;

import java.util.List;

@Getter
@Setter
public class StudentDetail {

    private List<StudentFolder> studentFolder;
    private List<StudentCourse> studentCourse;

}
