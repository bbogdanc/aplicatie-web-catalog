package com.bogdan.app.dataaccessobjects;

import com.bogdan.app.core.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

/**
 * Created by bbaloi on 31.07.2015.
 */

public interface TeacherDAO {
    public boolean isValid(Integer id,String lastName,String firstName,String password) throws EmptyResultDataAccessException;
    public Teacher getTeacher(Integer id);
    public void addStudent(String lastName,String firstName,
                           String fatherInitial,String username,
                           String password,
                           Integer courseId,Integer teacherId);
    public void addGrade(Integer studentId,Integer courseId,Integer teacherId,Integer nota);
    public List<Course> getCourses(Integer id);
    public void remStudent(Student s);
    public List<Student> getStudents(int courseId, int teacherId);
    public int CountStudents(int courseId, int teacherId);
    public Integer getStudentId(String username);
    public List<StudentwithGrades> getStudentAtCourse(int teacherId, int courseId);

}
