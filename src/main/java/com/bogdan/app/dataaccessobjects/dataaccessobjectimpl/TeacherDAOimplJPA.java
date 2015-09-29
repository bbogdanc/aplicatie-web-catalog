package com.bogdan.app.dataaccessobjects.dataaccessobjectimpl;

import com.bogdan.app.core.*;
import com.bogdan.app.dataaccessobjects.TeacherDAO;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bbaloi on 07.08.2015.
 */
public class TeacherDAOimplJPA implements TeacherDAO{

    @PersistenceUnit(unitName = "Student")
    EntityManagerFactory emf;

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public boolean isValid(Integer id, String lastName, String firstName, String password) throws EmptyResultDataAccessException {
        String Sql = "Select t.password from Teacher t  " +
                "WHERE t.teacherId = :id " +
                "AND t.lastName = :lastName " +
                "AND t.firstName = :firstName";

        EntityManager em = emf.createEntityManager();
        String check = (String) em.createQuery(Sql).setParameter("id", id).
                setParameter("lastName", lastName).
                setParameter("firstName", firstName).getSingleResult();

        em.close();
        return password.equals(check);

    }

    public Teacher getTeacher(Integer id) {
        EntityManager em = emf.createEntityManager();

        Teacher t = em.find(Teacher.class,id);
        em.close();
        return t;
    }

    public void addStudent(String lastName, String firstName, String fatherInitial, String username, String password, Integer courseId, Integer teacherId) {
        EntityManager em = emf.createEntityManager();

        Course course = em.find(Course.class, courseId);
        Teacher teacher = em.find(Teacher.class, teacherId);

        String Sql = "Select s from Student s where s.userName = :userName";
        try {
            Student s = (Student) em.createQuery(Sql).
                    setParameter("userName", username).
                    getSingleResult();
            s.getCourses().add(course);
        }catch(NoResultException e) {
            Student s = new Student();
            s.setLastName(lastName);
            s.setFirstName(firstName);
            s.setPassword(password);
            s.setFatherInitial(fatherInitial);
            s.setCourses(new HashSet<Course>());
            s.getCourses().add(course);
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            em.close();
        }
    }

    public void addGrade(Integer studentId, Integer courseId, Integer teacherId, Integer Nota) {

    }

    public List<Course> getCourses(Integer Id) {
        EntityManager em = emf.createEntityManager();
        Teacher t = em.find(Teacher.class,Id);
        em.close();

        return t.getCoursesTeacher();
    }

    public void remStudent(Student s) {

    }

    public List<Student> getStudents(int courseId, int teacherId) {
        EntityManager em = emf.createEntityManager();
        Course course = em.find(Course.class, courseId);
        Set<Student> students = course.getStudents();
        List<Student> stud = new ArrayList<Student>(students);
        em.close();
        return stud;
    }

    public int CountStudents(int courseId, int teacherId) {
        EntityManager em = emf.createEntityManager();
        Course course = em.find(Course.class, courseId);
        Set<Student> students = course.getStudents();
        em.close();

        return students.size();
    }

    public Integer getStudentId(String username) {
        String Sql = "Select s.studentId from Student s " +
                "where s.userName = :userName";
        EntityManager em = emf.createEntityManager();
        Integer Id = (Integer) em.createQuery(Sql).
                setParameter("userName",username).getSingleResult();
        em.close();

        return Id;
    }

    public List<StudentwithGrades> getStudentAtCourse(int teacherId, int courseId) {
        EntityManager em = emf.createEntityManager();
        Course course = em.find(Course.class, courseId);
        Set<Student> students = course.getStudents();

        if(course.getTeacher().getTeacherId() != teacherId) {
            return null;
        }
        List<StudentwithGrades> toReturn = new ArrayList<StudentwithGrades>();
        for(Student student:students) {
            StudentwithGrades studentwithGrades = new StudentwithGrades();
            studentwithGrades.setS(student);
            studentwithGrades.setGrade(new ArrayList<Integer>());
            Set<Course> courses = student.getCourses();

            for(Course course1:courses) {
                if(course1.equals(course)){
                    Set<Grade> grades = course1.getGrades();
                    for(Grade g:grades) {
                        studentwithGrades.getGrade().add(g.getGrade());
                    }
                    break;
                }
            }
            toReturn.add(studentwithGrades);
        }
        return toReturn;
    }


}
