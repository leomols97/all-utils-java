package atl.grade.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jlc
 */
public class StudentDto extends Dto<Integer> {

    private String firstName;
    private String lastName;
    private List<GradeDto> grades;

    public StudentDto(Integer key) {
        super(key);
    }

    public StudentDto(Integer key,String firstName, String lastName) {
        super(key);
        this.firstName = firstName;
        this.lastName = lastName;
        this.grades = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<GradeDto> getGrades() {
        return grades;
    }

}
