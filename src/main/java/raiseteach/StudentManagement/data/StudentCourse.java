package raiseteach.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

public class StudentCourse {

    @Getter
    @Setter

        private String id;
        private String datamineID;
        private String coursename;
        private String start;
        private String end;

    public String getCoursename() {
        return coursename;
    }
    }

