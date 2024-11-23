package raiseteach.StudentManagement.data;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
public class StudentFolder {

@NotBlank
@Pattern(regexp = "^\\d+$")
    private String id;

@NotBlank
    private String name;

@NotBlank
    private String namemini;

@NotBlank
    private String penname;

@NotBlank
@Email
    private String maleaddress;

@NotBlank
    private String address;


    private int age;

@NotBlank
    private String gender;

    private String remark;
    private String isDeleted;

//    public int getAge() {
//        return age;
//    }
//
//    public String getName() {
//        return name;
//    }
//        public void setName(String name) {
//            this.name = name;
//    }
}