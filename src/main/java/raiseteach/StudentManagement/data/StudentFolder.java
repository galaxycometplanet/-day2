package raiseteach.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFolder {


    private String id;
    private String name;
    private String namemini;
    private String penname;
    private String maleaddress;
    private String address;
    private int age;
    private String gender;
    private String remark;
    private String isDeleted;

    public int getAge() {
        return age;
    }
}