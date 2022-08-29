package g56133.atl.Mentoring.dto;

import java.util.Objects;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StudentDto {
    
    private String firstName;
    private String lastName;
    private Dto<Integer> key;
    
    public StudentDto(Integer matricule, String lastName, String firstName) {
        this.key = new Dto(matricule);
        this.lastName = lastName;
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public int getKey() {
        return this.key.getKey();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setKey(int matricule) {
        this.key = new Dto<>(matricule);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.firstName);
        hash = 37 * hash + Objects.hashCode(this.lastName);
        return hash;
    }
    
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        
        System.out.println(o instanceof StudentDto);
        if (!(o instanceof StudentDto)) {
            return false;
        }

        StudentDto tmpStudent = (StudentDto) o;
        System.out.println("o1 " + this.key.getKey() +" o2 " + tmpStudent.getKey());
        return this.key.getKey() == tmpStudent.getKey();
        
    }
    
    public String toString() {
        return "" + this.getKey() + "," + this.lastName + "," + this.firstName; 
    }
}
