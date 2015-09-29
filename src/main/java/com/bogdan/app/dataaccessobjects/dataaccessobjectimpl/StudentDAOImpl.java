package com.bogdan.app.dataaccessobjects.dataaccessobjectimpl;

import com.bogdan.app.core.Course;
import com.bogdan.app.core.GradesCourses;
import com.bogdan.app.core.Student;
import com.bogdan.app.dataaccessobjects.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bbaloi on 28.07.2015.
 */


public class StudentDAOImpl implements StudentDAO {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean isValid(String username, String password) {
        String Sql = "SELECT Password from Students " +
                "WHERE Username=?";
        try {
            return password.equals(jdbcTemplate.queryForObject(Sql, new Object[]{username}, new RowMapper<String>() {
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("Password");
                }
            }));
        }catch(EmptyResultDataAccessException e){
            return false;
        }
    }

    public Student getStudent(String username) {
        String Sql = " Select * from Students " +
                "Where Username=?";

        return jdbcTemplate.queryForObject(Sql, new Object[]{username},new RowMapper<Student>(){

            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student s = new Student();
                s.setStudentId(rs.getInt("S_ID"));
                s.setFirstName(rs.getString("FirstName"));
                s.setLastName(rs.getString("LastName"));
                s.setFatherInitial(rs.getString("FatherInitial"));
                s.setUserName(rs.getString("Username"));
                return s;

            }
        });


    }

    public List<GradesCourses> getGrades(String username) {
        String Sql = " Select * from Note n " +
                " INNER JOIN Studentrelations sr " +
                "on n.S_T_ID = sr.id " +
                "INNER JOIN Courses c " +
                "on sr.C_id =c.C_ID " +
                "INNER JOIN Students s " +
                "on sr.S_id=s.S_ID and s.Username=? ";

        return jdbcTemplate.query(Sql, new Object[]{username}, new RowMapper<GradesCourses>() {
            public GradesCourses mapRow(ResultSet rs, int rowNum) throws SQLException {

                String courseName = rs.getString("Name");
                Integer courseId = rs.getInt("C_ID");
                Integer nota = rs.getInt("Nota");

                return new GradesCourses(new Course(courseId,courseName),nota);
            }
        });

    }
}
