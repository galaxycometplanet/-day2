package raiseteach.StudentManagement.data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
public class StudentCourse {

    private String id;
    private String datamineID;
    private String coursename;
    private LocalDateTime start;
    private LocalDateTime end;



    public String getCoursename() {
        return coursename;
    }
}
