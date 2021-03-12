package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class RetrieveUsersFromDiabloDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/diablo?createDatabaseIfNotExist=true&useSSL=false";
        String user = "root";

        Properties properties = new Properties();
        properties.setProperty("user", user);

        try {
            Connection connection = DriverManager.getConnection(url, properties);
            String sql = "select  user_name,first_name,last_name,COUNT(ug.game_id) as 'count_games' from users as u \n" +
                    "                    join users_games as ug ON ug.user_id = u.id\n" +
                    "                    where u.user_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String getUsername  = resultSet.getString("user_name");
                String getFirstName = resultSet.getString("first_name");
                String getLastName = resultSet.getString("last_name");
                int getGamesCount = resultSet.getInt("count_games");

                if (getFirstName == null) {
                    System.out.println("No such user exists");
                    return;
                }

                System.out.println("User: " + getUsername);
                System.out.println(getFirstName + " " + getLastName + " has played " + getGamesCount + " games");
            } else {
                System.out.println("No such user exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
