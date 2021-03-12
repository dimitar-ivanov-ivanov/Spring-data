package solutions;

import entities.Town;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemoveObjects {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            List<Town> towns = entityManager
                    .createQuery("SELECT t FROM Town t", Town.class)
                    .getResultList();

            for (Town town :
                    towns) {
                if (town.getName().length() > 5) {
                    entityManager.detach(town);
                }
            }

            entityManager.getTransaction().begin();

            for (Town town :
                    towns) {
                if (entityManager.contains(town)) {
                    System.out.print(town.getName() + " -> ");
                    town.setName(town.getName().toLowerCase());
                    System.out.println(town.getName());
                }
            }

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
