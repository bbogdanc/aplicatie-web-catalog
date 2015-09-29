package com.bogdan.app.core;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by bbaloi on 28.07.2015.
 */
@Entity
@Table(name="Students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="S_ID",nullable = false)
    private int studentId;

    @Column(name = "Username")
    private String userName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "FatherInitial")
    private String fatherInitial;

    @Column(name = "Password")
    private String password;

    @ManyToMany
    @JoinTable(
            name="Studentrelations",
            joinColumns = {@JoinColumn(name = "S_id", referencedColumnName = "S_ID")},
            inverseJoinColumns = {@JoinColumn(name = "C_id", referencedColumnName = "C_ID")}
    )
    private Set<Course> courses;

    @OneToMany
    @JoinTable(name = "Studentrelations",
            joinColumns = {@JoinColumn(name = "S_id",referencedColumnName = "S_ID")},
            inverseJoinColumns = {@JoinColumn(name = "N_id",referencedColumnName = "N_ID")}

    )
    private List<Grade> grades;

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Student() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student(int studentId, String userName, String password , String lastName, String firstName, String fatherInitial) {
        this.studentId = studentId;
        this.userName = userName;
        this.password = password;

        this.lastName = lastName;
        this.firstName = firstName;
        this.fatherInitial = fatherInitial;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getFatherInitial() {
        return fatherInitial;
    }

    public void setFatherInitial(String fatherInitial) {
        this.fatherInitial = fatherInitial;
    }

}
