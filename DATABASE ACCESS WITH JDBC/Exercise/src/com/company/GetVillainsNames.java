package com.company;

import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        String url = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";
        String sql = "select name,COUNT(mv.villain_id) as count_minions from villains as v\n" +
                "JOIN minions_villains as mv on v.id = mv.villain_id\n" +
                "group by name\n" +
                "having count_minions > 15\n" +
                "order by count_minions desc";

        try {
            Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int count_minions = resultSet.getInt("count_minions");
                System.out.println(name + " " + count_minions);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
