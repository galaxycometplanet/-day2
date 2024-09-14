package raiseteach.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class StudentCourse {


        private String id;
        private String datamineID;
        private String coursename;
        private String start;
        private String end;

    public String getCoursename() {
        return coursename;
    }
    }

