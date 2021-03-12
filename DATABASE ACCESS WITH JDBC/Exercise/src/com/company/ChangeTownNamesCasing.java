package com.company;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        String url = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";
        String updateTowns = "UPDATE towns\n" +
                " SET name = upper(name)\n" +
                "WHERE country = ?";

        String getTownsFromCountry = "select name FROM towns\n" +
                "WHERE country  = ?";

        try {
            Connection connection = DriverManager.getConnection(url, properties);
            Scanner scanner = new Scanner(System.in);
            String country = scanner.nextLine();

            updateTowns(updateTowns, connection, country);
            ResultSet towns = getTownsFromCountry(getTownsFromCountry, connection, country);

            if (!towns.isBeforeFirst()) {
                System.out.println("No town names were affected.");
                return;
            }

            List<String> townNames = new ArrayList<>();
            while (towns.next()) {
                townNames.add(towns.getString("name"));
            }

            System.out.println(townNames.size() + " town names were affected. ");
            System.out.println("[" + String.join(", ", townNames) + "]");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet getTownsFromCountry(String getTownsFromCountry, Connection connection, String country) throws SQLException {
        PreparedStatement getTowns = connection.prepareStatement(getTownsFromCountry);
        getTowns.setString(1, country);
        return getTowns.executeQuery();
    }

    private static void updateTowns(String updateTowns, Connection connection, String country) throws SQLException {
        PreparedStatement updateTownsStatement = connection.prepareStatement(updateTowns);
        updateTownsStatement.setString(1, country);
        updateTownsStatement.executeUpdate();
    }
}
