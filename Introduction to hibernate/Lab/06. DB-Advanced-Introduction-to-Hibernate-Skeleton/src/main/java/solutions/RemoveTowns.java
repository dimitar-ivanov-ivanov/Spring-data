package solutions;

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class RemoveTowns {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();

        try {
            Town town = entityManager
                    .createQuery("SELECT t FROM Town AS t WHERE t.name =:townName", Town.class)
                    .setParameter("townName", townName)
                    .getSingleResult();

            List<Address> addressList = entityManager
                    .createQuery("SELECT a FROM Address AS a WHERE a.town.name =:townName", Address.class)
                    .setParameter("townName", townName)
                    .getResultList();

            System.out.printf("%d address/es in %s deleted%n", addressList.size(), town.getName());

            entityManager.getTransaction().begin();

            addressList.forEach(address -> {
                for (Employee employee : address.getEmployees()) {
                    employee.setAddress(null);
                }
                address.setTown(null);
                entityManager.remove(address);
            });

            entityManager.remove(town);
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
