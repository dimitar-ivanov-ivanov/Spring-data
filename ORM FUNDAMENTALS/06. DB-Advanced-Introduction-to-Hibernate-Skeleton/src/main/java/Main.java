import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        try {
            List<Town> towns = entityManager
                    .createQuery("SELECT name FROM Town t", Town.class)
                    .getResultList();

        } catch(Exception e){
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            factory.close();
        }
    }
}
