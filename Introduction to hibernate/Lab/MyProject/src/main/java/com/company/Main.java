package com.company;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
      EntityManagerFactory  factory = 
    		  Persistence.createEntityManagerFactory("soft_uni");
      EntityManager entityManager = factory.createEntityManager();
      
    }
}
