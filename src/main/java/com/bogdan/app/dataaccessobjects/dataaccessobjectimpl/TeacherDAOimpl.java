package com.bogdan.app.dataaccessobjects.dataaccessobjectimpl;

import com.bogdan.app.core.*;
import com.bogdan.app.dataaccessobjects.TeacherDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbaloi on 31.07.2015.
 */


public class TeacherDAOimpl implements TeacherDAO {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean isValid(Integer id, String lastName, String firstName, String password) throws EmptyResultDataAccessException {
        String Sql = "SELECT Password from Teachers WHERE T_ID=? " +
                " AND LastName=?" +
                " AND FirstName=?";
        try {
            return password.equals(jdbcTemplate.queryForObject(Sql,
                    new Object[]{id, lastName, firstName},
                    new RowMapper<String>() {
                        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return rs.getString("Password");
                        }
                    }));
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public Teacher getTeacher(Integer id) {
        String Sql = "Select * from Teachers WHERE T_ID=?";

        return jdbcTemplate.queryForObject(Sql, new Object[]{id}, new RowMapper<Teacher>() {
            public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
                Teacher t = new Teacher();
                t.setFirstName(rs.getString("FirstName"));
                t.setLastName(rs.getString("LastName"));
                t.setTeacherId(rs.getInt("T_ID"));
                return t;
            }
        });
    }

    public void addStudent(String lastName,String firstName,
                           String fatherInitial,String username,
                           String password,
                           Integer courseId,Integer teacherId) {
        String Sql ="Insert INTO Students(LastName,FirstName,FatherInitial,Username,Password) " +
                "VAlUES(?,?,?,?,?)";

        jdbcTemplate.update(Sql,lastName,firstName,fatherInitial,username,password);

        Integer studentId = getStudentId(username);

        Sql = "Insert INTO Studentrelations(S_id,C_id,T_id) VALUES (?,?,?)";

        jdbcTemplate.update(Sql,studentId,courseId,teacherId);
    }




    public Integer getStudentId(String username) {
        String Sql = "Select S_ID from Students where Username=?";
        return jdbcTemplate.queryForObject(Sql, new Object[]{username}, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("S_ID");
            }
        });
    }

    public List<Course> getCourses(Integer teacherId) {
       String Sql = "Select distinct * from Courses c " +
               "INNER JOIN Studentrelations sr " +
               "on c.C_ID = sr.C_id AND " +
               "sr.T_id = ? Group by c.C_ID";
        return jdbcTemplate.query(Sql, new Object[]{teacherId}, new RowMapper<Course>() {
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
                Course c = new Course();
                c.setCourseId(rs.getInt("C_ID"));
                c.setNume(rs.getString("Name"));
                return c;
            }
        });

    }

    public void addGrade(Integer studentId,Integer courseId,Integer teacherId,Integer nota){
        String Sql = "Select id from Studentrelations where S_id=? AND C_id=? AND T_id=?";

        Integer id = jdbcTemplate.queryForObject(Sql, new Object[]{studentId, courseId, teacherId}, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("id");
            }
        });

        Sql="Insert into Note (Nota,S_T_ID) Values(?,?)";

        jdbcTemplate.update(Sql,nota,id);
    }

    public void remStudent(Student s) {

    }


    public int CountStudents(int courseId, int teacherId) {
        String Sql = "Select Count(*) from Students s  " +
                "inner join Studentrelations sr on" +
                " s.S_ID = sr.S_id " +
                "AND sr.C_id = ? AND sr.T_id = ?";

        return jdbcTemplate.queryForObject(Sql,new Object[]{courseId,teacherId},Integer.class);
    }

    public List<StudentperCourse> getPopulation(int teacherId) {
        List<StudentperCourse> list = new ArrayList<StudentperCourse>();
        List<Course> courses = getCourses(teacherId);
        for(Course course:courses) {
            StudentperCourse spc = new StudentperCourse();
            spc.setCourse(course);
            spc.setNrStud(this.getStudents(course.getCourseId(),teacherId).size());
            list.add(spc);
        }

        return list;
    }

    public List<StudentwithGrades> getStudentAtCourse(int teacherId, int courseId) {
        String Sql = "Select * from Students s " +
                "INNER JOIN Studentrelations sr " +
                "ON s.S_ID=sr.S_id " +
                "INNER JOIN Courses c " +
                "On sr.C_id = c.C_ID AND c.C_ID = ? " +
                "INNER JOIN Teachers t on " +
                " sr.T_id = t.T_ID and t.T_ID = ? ";

        List<StudentwithGrades> list = new ArrayList<StudentwithGrades>();

        List<Student> students = jdbcTemplate.query(Sql, new Object[]{teacherId, courseId}, new RowMapper<Student>() {
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

        for (Student s:students) {
            StudentwithGrades swg = new StudentwithGrades();
            swg.setS(s);
            String Sql1 = "Select Nota from Note n " +
                    "INNER JOIN Studentrelations sr ON " +
                    "n.S_T_ID=sr.id " +
                    "INNER JOIN Students s on " +
                    "sr.S_id= s.S_ID and s.S_ID=? " +
                    "INNER JOIN Courses c on " +
                    "sr.C_id = c.C_ID AND c.C_ID=?";

            List<Integer> note = jdbcTemplate.query(Sql1, new Object[]{s.getStudentId(),courseId}, new RowMapper<Integer>() {
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getInt("Nota");
                }
            });
            swg.setGrade(note);

            list.add(swg);
        }

        return list;
    }


    public List<Student> getStudents(int courseId,int teacherId) {
        String Sql = "Select * from Students s" +
                " Inner Join Studentrelations sr on " +
                "s.S_ID = sr.S_id " +
                " AND sr.C_id = ? " +
                " AND sr.T_id = ? ";
        return jdbcTemplate.query(Sql, new Object[]{courseId, teacherId}, new RowMapper<Student>() {
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
}
