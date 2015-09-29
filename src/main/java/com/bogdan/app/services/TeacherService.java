package com.bogdan.app.services;

import com.bogdan.app.core.*;
import com.bogdan.app.dataaccessobjects.TeacherDAO;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by bbaloi on 31.07.2015.
 */
public interface TeacherService {

    public boolean isValid(Integer id,
                           String lastName,
                           String firstName,
                           String password);
    public boolean validateRegister(String lastName, String firstName,
                                    String fatherInitial, String username,
                                    String password);
    public Teacher getTeacher(Integer id);
    public void addStudent(String lastName, String firstName,
                           String fatherInitial, String username,
                           String password,
                           Integer courseId, Integer teacherId);
    public Integer getStudentId(String username);
    public void addGrade(String studentName, String courseName, Integer teacherId, Integer nota);
    public Integer getCourseID(Integer id, String courseName);
    public List<Course> getCourses(Integer id);
    public List<Student> getStudents(int courseId, int teacherId);
    public int CountStudents(int courseId, int teacherId);
    public List<StudentperCourse> getPopulation (int teacherId);
    public List<StudentwithGrades> getStudentAtCourse(int teacherId, int courseId);
}
