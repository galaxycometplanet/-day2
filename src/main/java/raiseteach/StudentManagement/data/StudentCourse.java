package raiseteach.StudentManagement.data;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Schema(description = "受講生コース情報")
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
