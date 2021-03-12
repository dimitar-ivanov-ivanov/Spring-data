package com.company.gringottsDatabase;

import com.company.gringottsDatabase.WizardDeposit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("wizard_deposits");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            WizardDeposit wizardDeposit = new WizardDeposit
                    (
                            "Dimitar", "Ivanov",
                            "Hello wizards",
                            22, "Dimitar", 55,
                            "Some deposit group",
                            new Date(), 500, 500.5,
                            200.32,
                            new Date(), false
                    );

            entityManager.persist(wizardDeposit);

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
