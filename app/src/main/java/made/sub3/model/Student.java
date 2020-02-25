package made.sub3.model;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class Student {
    private String id;
    private String name;
    private float studentNumber;
    private String email;
    ArrayList < Course > courseList;
    private String jurusan;
    private String fakultas;
    private float gpa;

}
