package com.learning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDelete {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "jpauser";
    static final String PWD = "jpapwd";

    public static void main(String[] args) {

        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PWD);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM students where surname = ?")){
            statement.setString(1, "Testov");
            int deletedRows = statement.executeUpdate();
            System.out.println("deletedRows = " + deletedRows);
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
