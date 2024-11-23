
package raiseteach.StudentManagement.domain;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raiseteach.StudentManagement.data.StudentCourse;
import raiseteach.StudentManagement.data.StudentFolder;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {

    // 単一の StudentFolder を保持する
    @Valid
    private StudentFolder studentFolder;

    // 複数の StudentCourse を保持するリスト
    @Valid
    private List<StudentCourse> studentCourse;



    // Lombok が自動で getter と setter を生成するため、以下は不要

    // 手動で定義する場合は、以下のように修正
    // getter
   // public List<StudentFolder> getStudentFolder() {
  //      return studentFolder;
  //  }

    // setter
 //   public void setStudentFolder(List<StudentFolder> studentFolder) {
  //      this.studentFolder = studentFolder;
 //   }
}
