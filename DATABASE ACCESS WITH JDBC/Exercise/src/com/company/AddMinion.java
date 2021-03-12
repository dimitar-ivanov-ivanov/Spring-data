package com.company;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class AddMinion {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        String url = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";

        String insertVillainSql = "insert into villains (name,evilness_factor)\n" +
                " Select ?,'evil'\n" +
                " Where not exists(select * from villains where name=?)";

        String insertTownsSql = "insert into towns (name,country)\n" +
                " Select ?,'none'\n" +
                " Where not exists(select * from towns where name=?)";

        String insertMinionSql = " insert into minions (name,age,town_id)\n" +
                "        Select ?,?,(select id from towns where name = ? limit 1) as townid\n" +
                "        Where not exists\n" +
                "        (select * from minions as m\n" +
                "        join towns as t \n" +
                "        where m.name=? and m.age = ? and t.name = ?)";

        String insertMinionVillainSql = "insert into minions_villains(minion_id,villain_id)\n" +
                "select ((select id from minions where name = ?),(select id from villains where name = ?))\n" +
                "Where not exists\n" +
                "(select * from minions_villains AS mv \n" +
                " join minions as m on m.id = mv.minion_id\n" +
                " join villains as v on v.id = mv.villain_id\n" +
                "where m.name = ? and v.name = ?)";

        try {
            Connection connection = DriverManager.getConnection(url, properties);
            Scanner scanner = new Scanner(System.in);
            String[] minionArgs = Arrays.stream(scanner.nextLine().split("[ :]")).filter(s -> !s.isEmpty()).toArray(String[]::new);
            String[] villainArgs = Arrays.stream(scanner.nextLine().split("[ :]")).filter(s -> !s.isEmpty()).toArray(String[]::new);

            String minionName = minionArgs[1];
            Integer minionAge = Integer.parseInt(minionArgs[2]);
            String townName = minionArgs[3];
            String villainName = villainArgs[1];

            insertVillainInDbIfNotExists(insertVillainSql, connection, villainName);

            insertTownIntoDbIfNotExists(insertTownsSql, connection, townName);

            insertMinionIntoToDb(insertMinionSql, connection, minionName, minionAge, townName);

            insertMinionVillainInDb(insertMinionVillainSql, connection, minionName, villainName);
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertMinionVillainInDb(String insertMinionVillainSql, Connection connection, String minionName, String villainName) throws SQLException {
        PreparedStatement insertMinionVillainStatement = connection.prepareStatement(insertMinionVillainSql);
        insertMinionVillainStatement.setString(1, minionName);
        insertMinionVillainStatement.setString(2, villainName);
        insertMinionVillainStatement.setString(3, minionName);
        insertMinionVillainStatement.setString(4, villainName);
        insertMinionVillainStatement.execute();
    }

    private static void insertMinionIntoToDb(String insertMinionSql, Connection connection, String minionName, Integer minionAge, String townName) throws SQLException {
        PreparedStatement insertMinionStatement = connection.prepareStatement(insertMinionSql);
        insertMinionStatement.setString(1, minionName);
        insertMinionStatement.setInt(2, minionAge);
        insertMinionStatement.setString(3, townName);
        insertMinionStatement.setString(4, minionName);
        insertMinionStatement.setInt(5, minionAge);
        insertMinionStatement.setString(6, townName);
        insertMinionStatement.execute();
    }

    private static void insertTownIntoDbIfNotExists(String insertTownsSql, Connection connection, String townName) throws SQLException {
        PreparedStatement insertTownStatement = connection.prepareStatement(insertTownsSql);
        insertTownStatement.setString(1, townName);
        insertTownStatement.setString(2, townName);
        insertTownStatement.execute();
    }

    private static boolean insertVillainInDbIfNotExists(String insertVillainSql, Connection connection, String villainName) throws SQLException {
        PreparedStatement insertVillainStatement = connection.prepareStatement(insertVillainSql);
        insertVillainStatement.setString(1, villainName);
        insertVillainStatement.setString(2, villainName);
        return insertVillainStatement.execute();
    }
}
