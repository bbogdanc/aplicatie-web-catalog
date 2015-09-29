package com.bogdan.app.core;

/**
 * Created by bbaloi on 31.07.2015.
 */
public class StudentperCourse {
    private Course course;
    Integer nrStud;

    public StudentperCourse() {

    }

    public StudentperCourse(Course course, Integer nrStud) {
        this.course = course;
        this.nrStud = nrStud;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getNrStud() {
        return nrStud;
    }

    public void setNrStud(Integer nrStud) {
        this.nrStud = nrStud;
    }
}
