package solutions;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        HashMap<String, BigDecimal> departmentTotalSalaries = new HashMap<>();

        try {
            entityManager
                    .createQuery("SELECT e FROM Employee AS e", Employee.class)
                    .getResultList()
                    .forEach(e -> {
                        String department = e.getDepartment().getName();
                        if (!departmentTotalSalaries.containsKey(department)) {
                            departmentTotalSalaries.put(department, new BigDecimal(0));
                        }
                        if (e.getSalary().compareTo(departmentTotalSalaries.get(department)) == 1) {
                            departmentTotalSalaries.put(department, e.getSalary());
                        }
                    });

            departmentTotalSalaries.forEach((department, maxSalary) -> {
                if (maxSalary.compareTo(BigDecimal.valueOf(30000)) == 1 ||
                        maxSalary.compareTo(BigDecimal.valueOf(70000)) == -1) {
                    System.out.println(department + " - " + maxSalary);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
