package solutions;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.department.name = 'Research and Development' ORDER BY e.salary, e.id", Employee.class)
                .getResultList()
                .forEach(employee ->
                        System.out.println(employee.getFirstName() + " " + employee.getLastName() + " from Research and Development - " + employee.getSalary()));

        entityManager.close();
        entityManagerFactory.close();
    }
}
