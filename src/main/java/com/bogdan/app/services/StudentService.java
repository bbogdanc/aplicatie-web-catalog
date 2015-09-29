package com.bogdan.app.services;

import com.bogdan.app.core.GradesCourses;
import com.bogdan.app.core.Student;
import com.bogdan.app.dataaccessobjects.StudentDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bbaloi on 28.07.2015.
 */
public interface StudentService {
    public boolean isValid(String username, String password);
    public Student getStudent(String username);
    public List<GradesCourses> getGrades(String username);
}
