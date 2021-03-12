package com.company;

import com.company.entities.ingredients.AmmoniumChloride;
import com.company.entities.ingredients.BasicIngredient;
import com.company.entities.ingredients.Mint;
import com.company.entities.ingredients.Nettle;
import com.company.entities.labels.BasicLabel;
import com.company.entities.shampoos.BasicShampoo;
import com.company.entities.shampoos.FreshNuke;

import javax.persistence.Basic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("shampoo_company");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            BasicIngredient am = new AmmoniumChloride();
            BasicIngredient mint = new Mint();
            BasicIngredient nettle = new Nettle();

            BasicLabel label =
                    new BasicLabel("Fresh Nuke Shampoo",
                            "Contains mint and nettle");

            BasicShampoo shampoo = new FreshNuke(label);
            shampoo.getIngredients().add(mint);
            shampoo.getIngredients().add(nettle);
            shampoo.getIngredients().add(am);
            entityManager.persist(shampoo);

            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
