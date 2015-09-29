package com.bogdan.app.services.impl;

import com.bogdan.app.core.GradesCourses;
import com.bogdan.app.core.Student;
import com.bogdan.app.dataaccessobjects.StudentDAO;
import com.bogdan.app.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by bbaloi on 28.07.2015.
 */


public class StudentSerivceImpl implements StudentService {


    private StudentDAO studentDAO;

    public StudentSerivceImpl(){

    }

    public StudentSerivceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public boolean isValid(String username, String password)  {
       return studentDAO.isValid(username,password);
    }

    public Student getStudent(String username) {
        return studentDAO.getStudent(username);
    }

    public List<GradesCourses> getGrades(String username) {
        return studentDAO.getGrades(username);
    }
}
