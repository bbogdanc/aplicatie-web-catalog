package com.bogdan.app.dataaccessobjects.dataaccessobjectimpl;

import com.bogdan.app.core.Course;
import com.bogdan.app.core.Grade;
import com.bogdan.app.core.GradesCourses;
import com.bogdan.app.core.Student;
import com.bogdan.app.dataaccessobjects.StudentDAO;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.*;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by bbaloi on 07.08.2015.
 */


public class StudentDAOimplJPA implements StudentDAO{

    @PersistenceUnit(unitName = "Student")
    private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public boolean isValid(String username, String password) throws EmptyResultDataAccessException {
        String Sql = "SELECT s.password from Student s" +
                " WHERE s.userName = :userName";

        EntityManager em = emf.createEntityManager();
        System.out.println("forta barca");
        String correct = (String) em.createQuery(Sql).setParameter("userName",username).
                getSingleResult();
        em.close();

        return password.equals(correct);
    }

    public Student getStudent(String username) {
        String Sql = "SELECT s from Student s " +
                "WHERE s.userName = :userName";
        EntityManager em = emf.createEntityManager();

        Student s = (Student) em.createQuery(Sql).
                setParameter("userName",username).getSingleResult();
        em.close();
        return s;
    }

    public List<GradesCourses> getGrades(String username) {
        EntityManager em = emf.createEntityManager();
        String Sql = "Select s from Student s " +
                "WHERE s.userName = :userName";

        Student s = (Student) em.createQuery(Sql).
                setParameter("userName", username).getSingleResult();

        List<GradesCourses> list = new ArrayList<GradesCourses>();

        Set<Course> courses = s.getCourses();
        Set<Grade> grades;
        System.out.println(courses.size());
        for(Course course:courses) {
            grades = course.getGrades();
                for(Grade g:grades) {
                    GradesCourses gradesCourses = new GradesCourses();
                    gradesCourses.setCourse(course);
                    gradesCourses.setGrade(g.getGrade());
                    list.add(gradesCourses);
                }
        }

        em.close();
        return list;
    }
}
