package com.company;

import java.sql.*;
import java.util.*;

public class PrintAllMinionNames {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        String url = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";
        String sql = "select name from minions";

        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<String> minions = new ArrayList<>();
            while (resultSet.next()) {
                minions.add(resultSet.getString("name"));
            }

            printMinionsOutOfOrder(minions);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printMinionsOutOfOrder(List<String> minions) {
        for (int counter = 0; counter < minions.size(); counter++) {
            System.out.println(minions.get(counter));
            System.out.println(minions.get(minions.size() - 1 - counter));
        }
    }
}
