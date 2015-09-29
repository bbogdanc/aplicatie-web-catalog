package com.bogdan.app.core;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bbaloi on 14.08.2015.
 */

@Entity
@Table(name = "Note")
public class Grade implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "N_ID")
    private Integer gradeId;

    @Column(name = "Grade")
    private Integer grade;

    public Grade() {
    }

    public Grade(Integer gradeId, Integer grade) {
        this.gradeId = gradeId;
        this.grade = grade;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}