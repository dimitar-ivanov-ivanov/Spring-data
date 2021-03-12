package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class RetrieveEmployeesFromSoft_Uni {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/soft_uni?createDatabaseIfNotExist=true&useSSL=false";
        String user = "root";

        Properties properties = new Properties();
        properties.setProperty("user", user);

        try {
            Connection connection = DriverManager.getConnection(url, properties);
            String sql = "select first_name,last_name from employees where salary > ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            Scanner scanner = new Scanner(System.in);
            double salary = Double.parseDouble(scanner.nextLine());
            statement.setDouble(1,salary);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
