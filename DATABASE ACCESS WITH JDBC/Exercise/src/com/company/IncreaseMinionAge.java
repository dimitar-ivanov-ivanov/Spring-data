package com.company;

import java.sql.*;
import java.util.*;

public class IncreaseMinionAge {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        String url = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";

        String updateMinions = "update minions\n" +
                "  set age = age + 1\n" +
                "  where id = ?";

        String getMinions = "select name,age from minions";

        try {
            Connection connection = DriverManager.getConnection(url, properties);

            Scanner scanner = new Scanner(System.in);
            int[] ids = Arrays
                    .stream(scanner.nextLine().split(" "))
                    .mapToInt(e -> Integer.parseInt(e))
                    .toArray();

            PreparedStatement updateMinionsStatement = connection.prepareStatement(updateMinions);
            for (int i = 0; i < ids.length; i++) {
                updateMinionsStatement.setInt(1, ids[i]);
                updateMinionsStatement.executeUpdate();
            }

            PreparedStatement getMinionsStatement = connection.prepareStatement(getMinions);
            ResultSet resultSet = getMinionsStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println(name + " " + age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
