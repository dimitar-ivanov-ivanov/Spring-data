package solutions;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();

        try {
            List<Employee> employees = entityManager
                    .createQuery("SELECT e FROM Employee AS e WHERE e.firstName LIKE :pattern", Employee.class)
                    .setParameter("pattern", pattern + "%")
                    .getResultList();

            employees.forEach(e -> System.out.printf("%s %s - %s - ($%.2d)%n",
                    e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
