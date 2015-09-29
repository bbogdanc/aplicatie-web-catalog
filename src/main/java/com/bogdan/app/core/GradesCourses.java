package com.bogdan.app.core;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Created by bbaloi on 30.07.2015.
 */

public class GradesCourses {

    
    private Course course;
    private Integer grade;

    public GradesCourses(){

    }

    public GradesCourses(Course course, Integer grade) {
        this.course = course;
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}
