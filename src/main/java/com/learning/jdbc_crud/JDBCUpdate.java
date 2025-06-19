package com.learning.jdbc_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCUpdate {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "jpauser";
    static final String PWD = "jpapwd";

    public static void main(String[] args) {

        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PWD);
            PreparedStatement statement = connection.prepareStatement("UPDATE students SET avg_grade = 7.5 where name = ?")){
            try(Scanner scanner = new Scanner(System.in)) {
                System.out.println("Enter name:");
                String enteredName = scanner.nextLine();
                
                // String sqlQuery = "UPDATE students SET avg_grade = 7.5 where name = '" + enteredName + "'";
                // statement.executeUpdate(sqlQuery);
 
                statement.setString(1, enteredName);
                // User' OR '5' = '5
                statement.executeUpdate();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
