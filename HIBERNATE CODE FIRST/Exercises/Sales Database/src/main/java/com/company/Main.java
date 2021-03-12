package com.company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("sales");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Product pr = new Product("pr1", 10, new BigDecimal("202.2"));
            Customer cu = new Customer("cu1", "cu@gmail.com", "abada2131");
            StoreLocation stl = new StoreLocation("loc1");
            Sale sale = new Sale(pr, cu, stl, new Date());

            entityManager.persist(cu);
            entityManager.persist(pr);
            entityManager.persist(stl);
            entityManager.persist(sale);

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
