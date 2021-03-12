package solutions;

import entities.Employee;
import entities.Town;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Scanner scanner = new Scanner(System.in);
            String[] employeeNames = scanner.nextLine().trim().split("\\s+");

            List<Employee> employees = entityManager
                    .createQuery("SELECT e FROM Employee AS e WHERE e.firstName = :firstName AND e.lastName = :lastName", Employee.class)
                    .setParameter("firstName", employeeNames[0])
                    .setParameter("lastName", employeeNames[1])
                    .getResultList();

            if (employees.isEmpty()) {
                System.out.println("No");
            } else{
                System.out.println("Yes");
            }


        } catch (Exception e) {

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
