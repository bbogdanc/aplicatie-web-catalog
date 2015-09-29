package com.bogdan.app.core;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by bbaloi on 23.07.2015.
 */
@Entity
@Table(name = "Courses")
public class Course implements Serializable{

    @Id
    @Column(name = "C_ID")
    @GeneratedValue
    private int courseId;

    @Column(name = "Name")
    private String nume;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    @ManyToOne
    @JoinTable(
            name = "Studentrelations",
            joinColumns = {@JoinColumn(name = "C_id",referencedColumnName = "C_ID")},
            inverseJoinColumns = {@JoinColumn(name = "T_id",referencedColumnName = "T_ID")}
    )
    private Teacher teacher;

    @OneToMany
    @JoinTable(
            name = "Studentrelations",
            joinColumns = {@JoinColumn(name = "C_id",referencedColumnName = "C_ID")},
            inverseJoinColumns = {@JoinColumn(name = "N_id",referencedColumnName = "N_ID")}
    )
    private Set<Grade> grades;


    public Course(){
    }

    public Course(int courseId, String nume) {

        this.courseId = courseId;
        this.nume = nume;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {

        this.courseId = courseId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
}
