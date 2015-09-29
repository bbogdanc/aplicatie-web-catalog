package com.bogdan.app.dataaccessobjects;

import com.bogdan.app.core.GradesCourses;
import com.bogdan.app.core.Student;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by bbaloi on 28.07.2015.
 */
public interface StudentDAO {
    public boolean isValid(String username, String password) throws EmptyResultDataAccessException;
    public Student getStudent(String username);
    public List<GradesCourses> getGrades(String username);

}
