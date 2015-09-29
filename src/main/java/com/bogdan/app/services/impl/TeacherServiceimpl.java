package com.bogdan.app.services.impl;

import com.bogdan.app.core.*;
import com.bogdan.app.dataaccessobjects.TeacherDAO;
import com.bogdan.app.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbaloi on 31.07.2015.
 */


public class TeacherServiceimpl implements TeacherService {


    private TeacherDAO teacherDAO;

    public TeacherServiceimpl(){

    }

    public TeacherServiceimpl(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }


    public boolean isValid(Integer id, String lastName,
                           String firstName, String password) {
        return teacherDAO.isValid(id, lastName, firstName, password);
    }

    public boolean validateRegister(String lastName, String firstName, String fatherInitial, String username, String password) {
        if (lastName.matches(".*\\d+.*") == true ||
                firstName.matches(".*\\d+.*") == true ||
                fatherInitial.matches(".*\\d+.*") == true ||
                username.matches(".*\\d+.*") == true
                )  {
            return false;
        }

        if(password.length() <=3)
            return false;

        return true;


    }

    public Teacher getTeacher(Integer id) {
        return teacherDAO.getTeacher(id);
    }

    public void addStudent(String lastName,String firstName,
                           String fatherInitial,String username,
                           String password,
                           Integer courseId,Integer teacherId) {
        teacherDAO.addStudent(lastName,firstName,fatherInitial,username,password,courseId,teacherId);
    }

    public Integer getStudentId(String username) {
        return teacherDAO.getStudentId(username);
    }

    public void addGrade(String studentName, String courseName, Integer teacherId, Integer nota) {
        Integer S_id = teacherDAO.getStudentId(studentName);
        Integer C_id = this.getCourseID(teacherId, courseName);
        teacherDAO.addGrade(S_id,C_id,teacherId,nota);
    }


    public Integer getCourseID(Integer id, String courseName) {
        List<Course> course = this.getCourses(id);
        for (Course c:course) {
            if(c.getNume().equals(courseName)) {
                return c.getCourseId();
            }
        }
        return null;
    }

    public List<Course> getCourses(Integer Id) {
        return teacherDAO.getCourses(Id);
    }

    public void remStudent(Student s) {
        teacherDAO.remStudent(s);
    }

    public List<Student> getStudents(int courseId, int teacherId) {
        return  teacherDAO.getStudents(courseId,teacherId);
    }

    public int CountStudents(int courseId, int teacherId) {
        return teacherDAO.CountStudents(courseId, teacherId);
    }

    public List<StudentperCourse> getPopulation(int teacherId) {
        List<StudentperCourse> list = new ArrayList<StudentperCourse>();
        List<Course> courses = getCourses(teacherId);
        for (Course course:courses) {
            StudentperCourse spc = new StudentperCourse();
            spc.setCourse(course);
            spc.setNrStud(this.getStudents(course.getCourseId(),teacherId).size());
            list.add(spc);
        }

        return list;
    }

    public List<StudentwithGrades> getStudentAtCourse(int teacherId, int courseId) {
        return teacherDAO.getStudentAtCourse(teacherId,courseId);
    }



}
