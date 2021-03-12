package com.company;

import com.company.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("football_betting");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Color blue = new Color("blue");
            Color red = new Color("red");
            Color grey = new Color("grey");
            Continent europe = new Continent("Europe");
            Country bulgaria = new Country("BUL", "Bulgaria");
            Town town = new Town("Haskovo", bulgaria);

            byte[] logo = new byte[]{1, 2, 3, 4, 5};
            Team levski = new Team("Levski", logo, "LEV", red, grey, town, 105);

            byte[] logo2 = new byte[]{5, 4, 3, 2, 1};
            Team cska = new Team("CSKA", logo2, "CSK", blue, grey, town, 220);

            europe.getCountries().add(bulgaria);
            bulgaria.getContinents().add(europe);

            entityManager.persist(blue);
            entityManager.persist(grey);
            entityManager.persist(europe);

            entityManager.persist(bulgaria);
            entityManager.persist(town);
            entityManager.persist(levski);

            CompetitionType competitionType = new CompetitionType("New competition");
            Competition competition = new Competition("Cm1", competitionType);
            Round round = new Round("1/8 FINAL");
            Game game = new Game(levski, cska, 1, 3, new Date(), 0.7, 0.2, 0.1, round, competition);

            Position pos = new Position("GK", "Goal keeper");
            Player player = new Player("Ivan Ivanov", 7, levski, pos, false);
            PlayerStatistics ps = new PlayerStatistics(game, player, 0, 0, 90);

            entityManager.persist(competitionType);
            entityManager.persist(competition);
            entityManager.persist(round);
            entityManager.persist(game);
            entityManager.persist(pos);
            entityManager.persist(player);
            entityManager.persist(ps);

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
