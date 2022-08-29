/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monTableView;

/**
 *
 * @author Leong Paeg-Hing
 */
public class Student {

    private int num;
    private String firstname;
    private String lastname;

    public Student(int num, String firstname, String lastname) {
        this.num = num;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getNum() {
        return num;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

}
