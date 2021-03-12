package com.company;

import com.company.entities.Course;
import com.company.entities.Student;
import com.company.entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("university");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Student st = new Student("a", "b", "033", 5.32, 89);
            Teacher t = new Teacher("c", "d", "03334", "cd@gmail.com", 5);
            Course c = new Course("v", "bb", new Date(), new Date(),100);

            c.getStudents().add(st);
            st.getCourses().add(c);
            c.setTeacher(t);
            t.getCourses().add(c);

            entityManager.persist(st);
            entityManager.persist(t);
            entityManager.persist(c);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
