package made.sub3.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class Course {
    private String id;
    private String name;
    private String teacherName;
    private float sks;
}
