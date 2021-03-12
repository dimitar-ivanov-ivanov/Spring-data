package solutions;

import entities.Employee;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        try {
            entityManager
                    .createQuery("SELECT e FROM Employee AS e " +
                            "WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services') " +
                            "ORDER BY e.id", Employee.class)
                    .getResultList()
                    .forEach(employee -> {
                        employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
                        System.out.printf("%s %s($%.2f)%n", employee.getFirstName(),
                                employee.getLastName(), employee.getSalary());
                    });

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
