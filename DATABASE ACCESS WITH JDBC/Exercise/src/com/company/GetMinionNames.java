package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        String url = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";

        String getMinionsSql = "select name,age from minions as m\n" +
                "join minions_villains as mv on m.id = mv.minion_id and mv.villain_id = ?";

        String getVillainNameSql = "select name from villains\n" +
                "where id = ?";

        try {
            Connection connection = DriverManager.getConnection(url, properties);
            Scanner scanner = new Scanner(System.in);
            int idOfVillain = Integer.parseInt(scanner.nextLine());

            PreparedStatement getMinionsStatement = connection.prepareStatement(getMinionsSql);
            PreparedStatement getVillainNameStatement = connection.prepareStatement(getVillainNameSql);

            getMinionsStatement.setInt(1, idOfVillain);
            getVillainNameStatement.setInt(1, idOfVillain);

            ResultSet getMinions = getMinionsStatement.executeQuery();
            ResultSet getVillainName = getVillainNameStatement.executeQuery();

            if(!getVillainName.next()){
                System.out.println("No villain with ID " + idOfVillain + " exists in the database.");
                return;
            }

            String villainName = getVillainName.getString("name");
            System.out.println("Villain: " + villainName);

            int countMinion = 0;
            while (getMinions.next()) {
                String minionName = getMinions.getString("name");
                int minionAge = getMinions.getInt("age");
                System.out.println(++countMinion + ". " + minionName + " " + minionAge);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
