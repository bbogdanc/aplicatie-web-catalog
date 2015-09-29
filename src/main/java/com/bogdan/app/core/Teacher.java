package com.bogdan.app.core;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bbaloi on 23.07.2015.
 */


@Entity
@Table(name = "Teachers")
public class Teacher implements Serializable {

    @Id
    @Column(name = "T_ID")
    @GeneratedValue
    private int teacherId;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "Password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Studentrelations",
            joinColumns = {@JoinColumn(name = "T_id",referencedColumnName = "T_ID")},
            inverseJoinColumns = {@JoinColumn(name = "C_id",referencedColumnName = "C_ID")}
    )
    private List<Course> coursesTeacher;


    public Teacher() {

    }

    public Teacher(int teacherId, String lastName, String firstName, String password) {
        this.teacherId = teacherId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
    }


    public List<Course> getCoursesTeacher() {
        return coursesTeacher;
    }

    public void setCoursesTeacher(List<Course> coursesTeacher) {
        this.coursesTeacher = coursesTeacher;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
