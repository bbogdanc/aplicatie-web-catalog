package com.bogdan.app.core;

import java.util.List;

/**
 * Created by bbaloi on 03.08.2015.
 */
public class StudentwithGrades {

    private Student s;
    private List<Integer> grade;

    public StudentwithGrades() {

    }

    public StudentwithGrades(Student s, List<Integer> grades) {

        this.s = s;
        this.grade = grades;
    }

    public Student getS() {
        return s;
    }

    public void setS(Student s) {
        this.s = s;
    }

    public List<Integer> getGrade() {
        return grade;
    }

    public void setGrade(List<Integer> grade) {
        this.grade = grade;
    }


}
