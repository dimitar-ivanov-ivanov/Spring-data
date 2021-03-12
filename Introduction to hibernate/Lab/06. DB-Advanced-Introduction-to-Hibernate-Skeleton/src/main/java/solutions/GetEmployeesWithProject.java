package solutions;

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.Scanner;

public class GetEmployeesWithProject {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.id=:id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        StringBuilder result = new StringBuilder();

        result.append(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getJobTitle() + System.lineSeparator());

        employee.getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> result.append("\t" + project.getName() + System.lineSeparator()));

        System.out.println(result);

        entityManager.close();
        entityManagerFactory.close();
    }
}
